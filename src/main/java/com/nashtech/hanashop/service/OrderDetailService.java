package com.nashtech.hanashop.service;

import com.nashtech.hanashop.data.dto.OrderDetailDTO;



import java.util.List;

public interface OrderDetailService  {
    List<OrderDetailDTO> findByOrder(String orderID);

}
