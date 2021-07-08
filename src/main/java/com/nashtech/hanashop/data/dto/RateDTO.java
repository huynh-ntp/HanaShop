package com.nashtech.hanashop.data.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RateDTO {
    private Long rateID;
    private String productID;
    private String userName;
    private Integer numOfStar;
    private String description;
    private Date dateRate;
}
