package com.nashtech.hanashop.service.Impl;

import com.nashtech.hanashop.data.dto.OrderDetailDTO;
import com.nashtech.hanashop.data.entity.OrderDetailEntity;
import com.nashtech.hanashop.data.entity.OrderEntity;
import com.nashtech.hanashop.data.mapper.OrderDetailMapper;
import com.nashtech.hanashop.repository.OrderDetailRepository;
import com.nashtech.hanashop.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    OrderDetailRepository orderDetailRepo;

    @Override
    public List<OrderDetailDTO> findByOrder(String orderID){
        OrderEntity order = new OrderEntity();
        order.setOrderID(orderID);
        List<OrderDetailEntity> listEntity = orderDetailRepo.findByOrder(order);
        List<OrderDetailDTO> listDTO = new ArrayList<>();
        if(listEntity!=null){
            listEntity.forEach(entity -> {
                listDTO.add(OrderDetailMapper.parseEntityToDTO(entity));
            });
        }
        return listDTO;
    }

}
