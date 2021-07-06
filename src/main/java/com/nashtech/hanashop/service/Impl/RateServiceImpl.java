package com.nashtech.hanashop.service.Impl;

import com.nashtech.hanashop.data.dto.RateDTO;
import com.nashtech.hanashop.data.entity.ProductEntity;
import com.nashtech.hanashop.data.entity.RateEntity;
import com.nashtech.hanashop.data.mapper.RateMapper;
import com.nashtech.hanashop.repository.RateRepository;
import com.nashtech.hanashop.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RateServiceImpl implements RateService {

    @Autowired
    RateRepository rateRepo;

    @Override
    public List<RateDTO> findByProductID (String productID){
        ProductEntity product = new ProductEntity();
        product.setProductID(productID);
        List<RateEntity> listEntity = rateRepo.findByProduct(product);
        List<RateDTO> listDTO = new ArrayList<>();
        if(listEntity!=null){
            listEntity.forEach(entity->{
                listDTO.add(RateMapper.parseEntityToDTO(entity));
            });
        }
        return listDTO;

    }
}
