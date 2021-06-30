package com.nashtech.hanashop.controller;

import com.nashtech.hanashop.data.dto.ProductDTO;
import com.nashtech.hanashop.data.entity.ProductEntity;
import com.nashtech.hanashop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/product")
    public ResponseEntity getAll(){
        List<ProductDTO> listProduct = productService.getAll();
        return ResponseEntity.ok(listProduct);
    }
    @PostMapping("/product")
    public ResponseEntity createProduct(@RequestBody ProductDTO dto){
        ProductDTO product = productService.createProduct(dto);
        return ResponseEntity.ok(product);
    }


    @DeleteMapping("/product/{productID}")
    public ResponseEntity deleteProduct(@PathVariable String productID){
        String status = productService.deleteProduct(productID);
        return ResponseEntity.ok(status);
    }

    @PutMapping("/product")
    public ResponseEntity updateProduct(@RequestBody ProductDTO dto){
        ProductDTO newProduct = productService.updateProduct(dto);
        return ResponseEntity.ok(newProduct);
    }
}
