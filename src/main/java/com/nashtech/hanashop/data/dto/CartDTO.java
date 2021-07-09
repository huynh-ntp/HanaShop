package com.nashtech.hanashop.data.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class CartDTO {
    private Map<String,ProductDTO> cart;
    private CustomerDTO customer;
}
