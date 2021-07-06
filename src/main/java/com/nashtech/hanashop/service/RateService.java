package com.nashtech.hanashop.service;

import com.nashtech.hanashop.data.dto.RateDTO;

import java.util.List;

public interface RateService {
    List<RateDTO> findByProductID (String productID);
}
