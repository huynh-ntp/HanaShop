package com.nashtech.hanashop.data.mapper;

import com.nashtech.hanashop.data.dto.ProductDTO;
import com.nashtech.hanashop.data.entity.CategoryEntity;
import com.nashtech.hanashop.data.entity.ProductEntity;

public class ProductMapper {
    public  ProductDTO parseEntityToDTO(ProductEntity entity){
        ProductDTO dto = new ProductDTO();
        dto.setProductID(entity.getProductID());
        dto.setProductName(entity.getProductName());
        dto.setImageSrc(entity.getImageSrc());
        dto.setDescription(entity.getDesscription());
        dto.setPrice(entity.getPrice());
        dto.setQuantity(entity.getQuantity());
        dto.setStatus(entity.getStatus());
        dto.setCreateDate(entity.getCreateDate());
        dto.setUpdateDate(entity.getUpdateDate());
        dto.setCategoryID(entity.getCategory().getCategoryID());
        return dto;
    }

    public  ProductEntity parseDTOToEntity(ProductDTO dto){
        ProductEntity entity = new ProductEntity();
        entity.setProductID(dto.getProductID());
        entity.setProductName(dto.getProductName());
        entity.setImageSrc(dto.getImageSrc());
        entity.setDesscription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setQuantity(dto.getQuantity());
        entity.setStatus(dto.getStatus());
        entity.setCreateDate(dto.getCreateDate());
        entity.setUpdateDate(dto.getUpdateDate());
        CategoryEntity category = new CategoryEntity();
        category.setCategoryID(dto.getCategoryID());
        entity.setCategory(category);
        return entity;
    }


}
