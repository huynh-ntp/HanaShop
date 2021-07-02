package com.nashtech.hanashop.service.Impl;

import com.nashtech.hanashop.data.dto.OrderDTO;
import com.nashtech.hanashop.data.entity.OrderEntity;
import com.nashtech.hanashop.data.entity.UserEntity;
import com.nashtech.hanashop.data.mapper.OrderMapper;
import com.nashtech.hanashop.repository.OrderRepository;
import com.nashtech.hanashop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepo;

    @Override
    public List<OrderDTO>  getAllOrderByUser(String userName){
        List<OrderDTO> listOrder = new ArrayList<>();
        UserEntity user = new UserEntity();
        user.setUserName(userName);
        List<OrderEntity> listOrderEntity = orderRepo.findByUser(user);
        if(listOrderEntity!=null){
            listOrderEntity.forEach(entity -> {
                listOrder.add(OrderMapper.parseEntityToDTO(entity));
            });
        }
        return listOrder;

    }
}
