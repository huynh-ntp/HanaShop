package com.nashtech.hanashop.data.mapper;

import com.nashtech.hanashop.data.dto.CustomerDTO;
import com.nashtech.hanashop.data.entity.CustomerEntity;
import com.nashtech.hanashop.data.entity.UserEntity;

public class CustomerMapper {
    public static CustomerEntity parseDTOToEntity(CustomerDTO dto){
        CustomerEntity entity = new CustomerEntity();
        UserEntity user = new UserEntity();
        user.setUserName(dto.getUserName());
        entity.setCustomerID(dto.getCustomerID());
        entity.setUser(user);
        entity.setAddress(dto.getAddress());
        entity.setPhone(dto.getPhone());
        return entity;
    }

    public static CustomerDTO parseEntityToDTO(CustomerEntity entity){
        CustomerDTO dto = new CustomerDTO();
        dto.setCustomerID(entity.getCustomerID());
        dto.setFullName(entity.getUser().getFullName());
        dto.setUserName(entity.getUser().getUserName());
        dto.setAddress(entity.getAddress());
        dto.setPhone(entity.getPhone());
        return dto;
    }
}
