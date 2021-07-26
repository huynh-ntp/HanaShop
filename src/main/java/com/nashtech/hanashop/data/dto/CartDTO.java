package com.nashtech.hanashop.data.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class CartDTO {
    private List<ProductDTO> cart;
    private CustomerDTO customer;
}
