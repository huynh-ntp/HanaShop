package com.nashtech.hanashop.data.mapper;

import com.nashtech.hanashop.data.dto.OrderDTO;
import com.nashtech.hanashop.data.dto.UserDTO;
import com.nashtech.hanashop.data.entity.OrderEntity;
import com.nashtech.hanashop.data.entity.UserEntity;
import org.apache.catalina.User;

public class OrderMapper {
    public static OrderDTO parseEntityToDTO(OrderEntity entity){
        OrderDTO dto = new OrderDTO();
        dto.setOrderID(entity.getOrderID());
        dto.setTotalPrice(entity.getTotalPrice());
        dto.setAddress(entity.getAddress());
        dto.setPhone(entity.getPhone());
        dto.setOrderDate(entity.getOrderDate());
        dto.setTypePay(entity.getTypePay());
        dto.setUserName(entity.getUser().getUserName());
        return dto;
    }

    public static OrderEntity parseEntityToDTO(OrderDTO dto){
        OrderEntity entity = new OrderEntity();
        entity.setOrderID(dto.getOrderID());
        entity.setTotalPrice(dto.getTotalPrice());
        entity.setAddress(dto.getAddress());
        entity.setPhone(dto.getPhone());
        entity.setOrderDate(dto.getOrderDate());
        entity.setTypePay(dto.getTypePay());
        UserEntity user = new UserEntity();
        user.setUserName(dto.getUserName());
        entity.setUser(user);
        return entity;
    }
}
