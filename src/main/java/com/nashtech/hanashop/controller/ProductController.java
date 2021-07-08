package com.nashtech.hanashop.controller;

import com.nashtech.hanashop.data.dto.ProductDTO;
import com.nashtech.hanashop.data.entity.ProductEntity;
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
//    @PreAuthorize("hasAnyRole('CUS','AD')")
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

    @GetMapping("/category/{categoryID}")
    public ResponseEntity<?> findByCategory(@PathVariable("categoryID") String categoryID){
        List<ProductDTO> listProduct = productService.findByCategory(categoryID);
        if(listProduct.size()==0){
            return ResponseEntity.ok("Don't have any product");
        }
        return ResponseEntity.ok(listProduct);
    }

    @PostMapping()
    @PreAuthorize("hasRole('AD')")
    public ResponseEntity createProduct(@RequestBody ProductDTO dto){
        String validStatus = validData(dto,"create");
        if(!validStatus.equals("Valid")){
            return ResponseEntity.badRequest().body(validStatus);
        }
        ProductDTO product = productService.createProduct(dto);
        return ResponseEntity.ok(product);
    }


    @DeleteMapping("/{productID}")
    @PreAuthorize("hasRole('AD')")
    public ResponseEntity deleteProduct(@PathVariable String productID){
        String status = productService.deleteProduct(productID);
        return ResponseEntity.ok(status);
    }

    @PutMapping()
    @PreAuthorize("hasRole('AD')")
    public ResponseEntity updateProduct(@RequestBody ProductDTO dto){
        String validStatus = validData(dto,"update");
        if(!validStatus.equals("Valid")){
            return ResponseEntity.badRequest().body(validStatus);
        }
        ProductDTO newProduct = productService.updateProduct(dto);
        return ResponseEntity.ok(newProduct);
    }

    public String validData(ProductDTO dto,String status){
        if(status.equals("update")){
            if(dto.getProductID()==null || dto.getProductID().trim().isEmpty()){
                return "ProductID not blank";
            }
        }
        if(dto.getProductName()==null || dto.getProductName().trim().isEmpty()){
            return "Product not blank";
        }
        if(dto.getImageSrc()==null || dto.getImageSrc().trim().isEmpty()){
            return "Image not blank";
        }
        if(dto.getDesscription()==null || dto.getDesscription().trim().isEmpty()){
            return "Description not blank";
        }
        if(dto.getPrice()==null){
            return "Price not blank";
        }
        if(dto.getPrice()<=0){
            return "Price must be greater than 0";
        }

        if(dto.getQuantity()==null){
            return "Price not blank";
        }
        if(dto.getQuantity()<=0){
            return "Quantity must be greater than 0";
        }

        if(dto.getCategoryID()==null || dto.getCategoryID().trim().isEmpty()){
            return "Category not blank";
        }
        return "Valid";

    }
}
