package com.nashtech.hanashop.data.dto;

import lombok.Data;

@Data
public class CustomerDTO {
    private Long customerID;
    private String address;
    private String phone;
    private String userName;
    private String fullName;

}
