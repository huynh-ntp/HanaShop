package com.nashtech.hanashop.service;

import com.nashtech.hanashop.data.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getAllOrderByUser(String userName);

}
