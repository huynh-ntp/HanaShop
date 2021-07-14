package com.servicetest;

import com.nashtech.hanashop.data.dto.RateDTO;
import com.nashtech.hanashop.data.entity.ProductEntity;
import com.nashtech.hanashop.data.entity.RateEntity;
import com.nashtech.hanashop.data.entity.UserEntity;
import com.nashtech.hanashop.repository.RateRepository;
import com.nashtech.hanashop.service.Impl.RateServiceImpl;
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
public class RateServiceTest {
    @Mock
    RateRepository rateRepository;
    @InjectMocks
    RateServiceImpl rateService;
    private RateEntity entity = new RateEntity();
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        entity.setUser(new UserEntity());
        entity.setProduct(new ProductEntity());
    }



    @Test
    public void findByProductID_ReturnList(){

        List<RateEntity> list = new ArrayList<>();
        list.add(entity);
        Mockito.when(rateRepository.findByProduct(Mockito.anyObject())).thenReturn(list);
        Assertions.assertEquals(1,rateService.findByProductID(Mockito.anyString()).size());
    }

    @Test
    public void rating_ReturnRateDTO(){
        Mockito.when(rateRepository.save(Mockito.anyObject())).thenReturn(entity);
        Assertions.assertNotNull(rateService.rating(new RateDTO()));
    }
}
