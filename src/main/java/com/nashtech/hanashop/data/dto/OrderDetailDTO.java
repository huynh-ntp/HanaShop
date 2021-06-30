package com.nashtech.hanashop.data.dto;

import lombok.Data;

@Data
public class OrderDetailDTO {
    private String detailID;
    private String orderID;
    private String productID;
    private Float price;
    private Integer quantity;

}
