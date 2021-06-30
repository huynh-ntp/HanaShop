package com.nashtech.hanashop.data.dto;

import lombok.Data;

import java.util.Date;

@Data
public class OrderDTO {
    private String orderID;
    private Float totalPrice;
    private String address;
    private String phone;
    private Date orderDate;
    private String typePay;
    private String userName;

}
