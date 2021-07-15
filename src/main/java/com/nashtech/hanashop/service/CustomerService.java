package com.nashtech.hanashop.service;

import com.nashtech.hanashop.data.dto.CustomerDTO;

public interface CustomerService {
    CustomerDTO findByUserName(String userName);
    CustomerDTO createCustomer(CustomerDTO dto);
    CustomerDTO updateCustomer(CustomerDTO dto);
}
