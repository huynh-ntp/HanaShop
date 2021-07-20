package com.nashtech.hanashop.controller;

import com.nashtech.hanashop.data.dto.ErrorProductDTO;
import com.nashtech.hanashop.data.dto.ProductDTO;
import com.nashtech.hanashop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;


    @GetMapping()
    public ResponseEntity getAll(){
        List<ProductDTO> listProduct = productService.getAll();
        return ResponseEntity.ok(listProduct);
    }

    @GetMapping("/{productName}")
    public ResponseEntity<?> findByName(@PathVariable("productName") String productName){
        List<ProductDTO> listProduct = productService.findByName(productName);
        if(listProduct.size()==0){
            return ResponseEntity.ok("Don't have any product");
        }
        return ResponseEntity.ok(listProduct);
    }

    @GetMapping("/id/{productID}")
    public ResponseEntity<?> findByProductID(@PathVariable("productID") String productID){
        ProductDTO product = productService.findByProductID(productID);
        if(product!=null){
            return ResponseEntity
                    .ok()
                    .body(product);
        }
        return ResponseEntity.badRequest().body("Don't have any product");
    }

    @GetMapping("/category/{categoryID}")
    public ResponseEntity<?> findByCategory(@PathVariable("categoryID") String categoryID){
        List<ProductDTO> listProduct = productService.findByCategory(categoryID);
        if(listProduct.size()==0){
            return ResponseEntity.badRequest().body("Don't have any product");
        }
        return ResponseEntity.ok(listProduct);
    }

    @PostMapping()
    @PreAuthorize("hasRole('AD')")
    public ResponseEntity createProduct(@RequestBody ProductDTO dto){
        ErrorProductDTO error = validData(dto,"create");
        if(isError(error)){
            return ResponseEntity
                    .badRequest()
                    .body(error);
        }
        ProductDTO product = productService.createProduct(dto);
        return ResponseEntity.ok(product);
    }


    @DeleteMapping("/{productID}")
    @PreAuthorize("hasRole('AD')")
    public ResponseEntity<?> deleteProduct(@PathVariable String productID){
        String status = productService.deleteProduct(productID);

        if(status.equals("Delete product success")){
            return ResponseEntity.ok(status);
        }
        return ResponseEntity.badRequest().body(status);

    }

    @PutMapping()
    @PreAuthorize("hasRole('AD')")
    public ResponseEntity updateProduct(@RequestBody ProductDTO dto){
        ErrorProductDTO error = validData(dto,"update");
        if(isError(error)){
            return ResponseEntity
                    .badRequest()
                    .body(error);
        }
        ProductDTO newProduct = productService.updateProduct(dto);
        if(newProduct==null){
            return ResponseEntity
                    .badRequest()
                    .body("Product not exist");
        }
        return ResponseEntity.ok(newProduct);
    }

    private boolean isError(ErrorProductDTO error){
        if(!error.getProductIDError().isEmpty() || !error.getProductNameError().isEmpty()
                || !error.getPriceError().isEmpty() || !error.getDescriptionError().isEmpty()
                || !error.getQuantityError().isEmpty() || !error.getImageSrcError().isEmpty()
                || !error.getCategoryIDError().isEmpty()){
            return true;
        }
        return  false;
    }

    private ErrorProductDTO validData(ProductDTO dto,String status){
        ErrorProductDTO error = new ErrorProductDTO("","","",
                "","","","");
        if(status.equals("update")){
            if(dto.getProductID()==null || dto.getProductID().trim().isEmpty()){
                error.setProductIDError("ProductID is not blank");
            }
        }
        if(dto.getProductName()==null || dto.getProductName().trim().isEmpty()){
            error.setProductNameError("Product name is not blank");

        }
        if(dto.getImageSrc()==null || dto.getImageSrc().trim().isEmpty()){
            error.setImageSrcError("Image is not blank");

        }
        if(dto.getDescription()==null || dto.getDescription().trim().isEmpty()){
            error.setDescriptionError("Description is not blank");
        }
        if(dto.getPrice()==null){
            error.setPriceError("Price is not blank");
        }else if (dto.getPrice()<=0){
            error.setPriceError("Price must be greater than 0");
        }
        if(dto.getQuantity()==null){
            error.setQuantityError("Quantity is  not blank");

        } else if(dto.getQuantity()<=0){
            error.setQuantityError("Quantity must be greater than 0");
        }
        if(dto.getCategoryID()==null || dto.getCategoryID().trim().isEmpty()){
            error.setCategoryIDError("Category not blank");
        }
        return error;

    }
}
