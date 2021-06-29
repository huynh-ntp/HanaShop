package com.example.hanashop.data.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RateDTO {
    private String rateID;
    private String productID;
    private String userName;
    private Integer numOfStar;
    private String description;
    private Date dateRate;
}
