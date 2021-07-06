package com.nashtech.hanashop.data.mapper;

import com.nashtech.hanashop.data.dto.RateDTO;
import com.nashtech.hanashop.data.entity.ProductEntity;
import com.nashtech.hanashop.data.entity.RateEntity;
import com.nashtech.hanashop.data.entity.UserEntity;

public class RateMapper {
    public static RateDTO parseEntityToDTO(RateEntity entity){
        RateDTO dto = new RateDTO();
        dto.setRateID(entity.getRateID());
        dto.setDateRate(entity.getDateRate());
        dto.setUserName(entity.getUser().getUserName());
        dto.setDescription(entity.getDescription());
        dto.setProductID(entity.getProduct().getProductID());
        dto.setNumOfStar(entity.getNumOfStar());
        return dto;
    }

    public static RateEntity parseDTOToEntity(RateDTO dto){
        RateEntity entity = new RateEntity();
        entity.setRateID(dto.getRateID());
        entity.setDateRate(dto.getDateRate());
        UserEntity user = new UserEntity();
        user.setUserName(dto.getUserName());
        entity.setUser(user);
        ProductEntity product = new ProductEntity();
        product.setProductID(dto.getProductID());
        entity.setProduct(product);
        entity.setDescription(dto.getDescription());
        entity.setNumOfStar(dto.getNumOfStar());
        return entity;

    }
}
