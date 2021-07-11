package com.nashtech.hanashop.data.dto;

import lombok.Data;

import java.util.Date;
@Data
public class ProductDTO {
    private String productID;
    private String productName;
    private String imageSrc;
    private String description;
    private Float price;
    private Integer quantity;
    private Boolean status;
    private Date createDate;
    private Date updateDate;
    private String categoryID;


}
