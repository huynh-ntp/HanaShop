package com.nashtech.hanashop.controller;

import com.nashtech.hanashop.data.dto.CategoryDTO;
import com.nashtech.hanashop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/category")
    public ResponseEntity getAll(){
        List<CategoryDTO> listCategory = categoryService.getAll();
        return ResponseEntity.ok(listCategory);
    }
    

}
