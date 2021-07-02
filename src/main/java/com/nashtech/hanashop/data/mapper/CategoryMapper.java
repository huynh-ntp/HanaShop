package com.nashtech.hanashop.data.mapper;

import com.nashtech.hanashop.data.dto.CategoryDTO;
import com.nashtech.hanashop.data.entity.CategoryEntity;

public class CategoryMapper {
    public static CategoryDTO parseEntityToDTO(CategoryEntity entity){
        CategoryDTO dto = new CategoryDTO();
        dto.setCategoryID(entity.getCategoryID());
        dto.setCategoryName(entity.getCategoryName());
        return dto;

    }

    public static CategoryEntity parseDTOToEntity(CategoryDTO dto){
        CategoryEntity entity = new CategoryEntity();
        entity.setCategoryID(dto.getCategoryID());
        entity.setCategoryName(dto.getCategoryName());
        return entity;

    }
}
