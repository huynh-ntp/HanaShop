package com.servicetest;


import com.nashtech.hanashop.data.entity.*;
import com.nashtech.hanashop.repository.OrderDetailRepository;
import com.nashtech.hanashop.service.Impl.OrderDetailServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class OrderDetailServiceTest {

    @Mock
    OrderDetailRepository orderDetailRepository;

    @InjectMocks
    OrderDetailServiceImpl detailService;

    private List<OrderDetailEntity> orderDetailEntityList = new ArrayList<>();
    OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        orderDetailEntity.setOrder(new OrderEntity());
        orderDetailEntity.setProduct(new ProductEntity());
        orderDetailEntityList.add(orderDetailEntity);
    }

    @Test
    public void findByOrder(){
        Mockito.when(orderDetailRepository.findByOrder(Mockito.any())).thenReturn(orderDetailEntityList);
        Assertions.assertEquals(1,detailService.findByOrder(Mockito.any()).size());
    }
}
