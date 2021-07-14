package com.servicetest;
import com.nashtech.hanashop.data.dto.CartDTO;
import com.nashtech.hanashop.data.dto.CustomerDTO;
import com.nashtech.hanashop.data.dto.ProductDTO;
import com.nashtech.hanashop.data.entity.*;
import com.nashtech.hanashop.repository.OrderDetailRepository;
import com.nashtech.hanashop.repository.OrderRepository;
import com.nashtech.hanashop.repository.ProductRepository;
import com.nashtech.hanashop.service.Impl.OrderDetailServiceImpl;
import com.nashtech.hanashop.service.Impl.OrderServiceImpl;
import com.nashtech.hanashop.service.Impl.ProductServiceImpl;
import org.aspectj.weaver.ast.Or;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {
    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    OrderServiceImpl orderService;

    private List<OrderEntity> orderEntityList = new ArrayList<>();
    OrderEntity orderEntity = new OrderEntity();
    private Map<String, ProductDTO> cart = new HashMap<>();
    private CartDTO cartDTO = new CartDTO();
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        orderEntity.setUser(new UserEntity());
        orderEntityList.add(orderEntity);
        cartDTO.setCart(cart);
        cartDTO.setCustomer(new CustomerDTO());
    }

    @Test
    public void getAllOrderByUser_ReturnListOrder(){
        Mockito.when(orderRepository.findByUser(Mockito.anyObject())).thenReturn(orderEntityList);
        Assertions.assertEquals(1,orderService.getAllOrderByUser(Mockito.anyString()).size());
    }

    @Test
    public void order_ReturnOrderDTO(){
        Mockito.when(orderRepository.save(Mockito.anyObject())).thenReturn(orderEntity);
        Assertions.assertNotNull(orderService.order(cartDTO));
    }
}
