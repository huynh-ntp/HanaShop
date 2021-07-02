package com.nashtech.hanashop.data.mapper;


import com.nashtech.hanashop.data.dto.OrderDetailDTO;
import com.nashtech.hanashop.data.entity.OrderDetailEntity;
import com.nashtech.hanashop.data.entity.OrderEntity;
import com.nashtech.hanashop.data.entity.ProductEntity;

public class OrderDetailMapper {
    public static OrderDetailDTO parseEntityToDTO(OrderDetailEntity entity ){
        OrderDetailDTO dto = new OrderDetailDTO();
        dto.setDetailID(entity.getDetailID());
        dto.setOrderID(entity.getOrder().getOrderID());
        dto.setProductID(entity.getProduct().getProductID());
        dto.setQuantity(entity.getQuantity());
        dto.setPrice(entity.getPrice());
        return dto;
    }

    public static OrderDetailEntity parseDTOToEntity(OrderDetailDTO dto ){
        OrderDetailEntity entity = new OrderDetailEntity();
        entity.setDetailID(entity.getDetailID());
        OrderEntity order = new OrderEntity();
        order.setOrderID(dto.getOrderID());
        ProductEntity product = new ProductEntity();
        product.setProductID(dto.getProductID());
        entity.setProduct(product);
        entity.setPrice(dto.getPrice());
        entity.setQuantity(dto.getQuantity());
        return entity;
    }



}
