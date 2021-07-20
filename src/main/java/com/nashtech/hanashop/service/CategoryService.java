package com.nashtech.hanashop.service;

import com.nashtech.hanashop.data.dto.CategoryDTO;
import com.nashtech.hanashop.data.entity.CategoryEntity;

import java.util.List;

public interface CategoryService  {
    List<CategoryDTO> getAll();
    CategoryDTO create(CategoryDTO categoryDTO);
}
