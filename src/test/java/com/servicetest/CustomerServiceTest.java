package com.servicetest;

import com.nashtech.hanashop.data.dto.CustomerDTO;
import com.nashtech.hanashop.data.entity.CustomerEntity;
import com.nashtech.hanashop.data.entity.OrderEntity;
import com.nashtech.hanashop.data.entity.ProductEntity;
import com.nashtech.hanashop.data.entity.UserEntity;
import com.nashtech.hanashop.repository.CustomerRepository;
import com.nashtech.hanashop.service.Impl.CustomerServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private CustomerServiceImpl customerService;

    CustomerEntity entity = new CustomerEntity();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        entity.setUser(new UserEntity());
    }

    @Test
    public void createCustomer_ReturnCustomerDTO(){
        Mockito.when(customerRepository.save(Mockito.any())).thenReturn(entity);
        Assertions.assertNotNull(customerService.createCustomer(new CustomerDTO()));
    }

    @Test
    public void findByUserName_ReturnCustomerDTO(){
        Mockito.when(customerRepository.findByUser(Mockito.any())).thenReturn(entity);
        Assertions.assertNotNull(customerService.findByUserName(Mockito.anyString()));
    }

    @Test
    public void findByUserName_ReturnNull(){
        Mockito.when(customerRepository.findByUser(Mockito.any())).thenReturn(null);
        Assertions.assertEquals(null,customerService.findByUserName(Mockito.anyString()));
    }

    @Test
    public void updateCustomerSuccess_ReturnCustomerDTO(){
        Mockito.when(customerRepository.existsById(Mockito.any())).thenReturn(true);
        Mockito.when(customerRepository.findByUser(Mockito.any())).thenReturn(entity);
        Mockito.when(customerRepository.save(Mockito.any())).thenReturn(entity);
        Assertions.assertNotNull(customerService.updateCustomer(new CustomerDTO()));
    }

    @Test
    public void updateCustomerIdNotExist_ReturnNull(){
        Mockito.when(customerRepository.existsById(Mockito.any())).thenReturn(false);
        Assertions.assertEquals(null,customerService.updateCustomer(new CustomerDTO()));
    }

    @Test
    public void updateCustomerUsernameNotExist_ReturnNull(){
        Mockito.when(customerRepository.existsById(Mockito.any())).thenReturn(true);
        Mockito.when(customerRepository.findByUser(Mockito.any())).thenReturn(null);
        Assertions.assertEquals(null,customerService.updateCustomer(new CustomerDTO()));
    }




}
