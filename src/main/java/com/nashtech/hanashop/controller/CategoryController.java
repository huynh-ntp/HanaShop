package com.nashtech.hanashop.controller;

import com.nashtech.hanashop.data.dto.CategoryDTO;
import com.nashtech.hanashop.data.dto.ErrorCategoryDTO;
import com.nashtech.hanashop.data.dto.ErrorProductDTO;
import com.nashtech.hanashop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping()
    public ResponseEntity getAll(){
        List<CategoryDTO> listCategory = categoryService.getAll();
        return ResponseEntity.ok(listCategory);
    }

    @PostMapping
    @PreAuthorize("hasRole('AD')")
    public ResponseEntity<?> create(@RequestBody CategoryDTO cate){
        ErrorCategoryDTO error = checkValid(cate);
        if(isError(error)){
            return ResponseEntity.badRequest().body(error);
        }
        CategoryDTO cateDTO = categoryService.create(cate);
        if(cateDTO==null){
            error.setCategoryIDError("Category ID is already taken!");
            return ResponseEntity.badRequest().body(error);
        }
        return ResponseEntity.ok(cateDTO);

    }

    private boolean isError(ErrorCategoryDTO error){
        if(!error.getCategoryIDError().trim().isEmpty() || !error.getCategoryNameError().trim().isEmpty()){
            return true;
        }
        return false;
    }

    private ErrorCategoryDTO checkValid(CategoryDTO cate){
        ErrorCategoryDTO error = new ErrorCategoryDTO("","");
        if(cate.getCategoryID().trim().isEmpty()|| cate.getCategoryID()==null ){
            error.setCategoryIDError("Category id is not blank");
        }
        if(cate.getCategoryName().trim().isEmpty() || cate.getCategoryName()==null){
            error.setCategoryNameError("Category name is not blank");
        }
        return error;
    }

}
