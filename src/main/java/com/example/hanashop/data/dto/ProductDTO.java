package com.example.hanashop.data.dto;

import lombok.Data;

import java.util.Date;
@Data
public class ProductDTO {
    private String productID;
    private String productName;
    private String imageSrc;
    private String desscription;
    private Float price;
    private Integer quantity;
    private Boolean status;
    private Date createDate;
    private Date updateDate;


}
