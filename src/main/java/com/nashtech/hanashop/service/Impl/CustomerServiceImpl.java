package com.nashtech.hanashop.service.Impl;

import com.nashtech.hanashop.data.dto.CustomerDTO;
import com.nashtech.hanashop.data.entity.CustomerEntity;
import com.nashtech.hanashop.data.entity.UserEntity;
import com.nashtech.hanashop.data.mapper.CustomerMapper;
import com.nashtech.hanashop.repository.CustomerRepository;
import com.nashtech.hanashop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository cusRepo;

    @Override
    public CustomerDTO findByUserName(String userName) {
        UserEntity user = new UserEntity();
        user.setUserName(userName);
        CustomerEntity entity = cusRepo.findByUser(user);
        if(entity!=null){
            return CustomerMapper.parseEntityToDTO(entity);
        }
        return null;
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO dto) {
       CustomerEntity entity = CustomerMapper.parseDTOToEntity(dto);
       CustomerEntity customerCur = cusRepo.save(entity);
       return CustomerMapper.parseEntityToDTO(customerCur);
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO dto) {
        if(!cusRepo.existsById(dto.getCustomerID())){
            return null;
        }
        UserEntity user = new UserEntity();
        user.setUserName(dto.getUserName());
        if((cusRepo.findByUser(user)==null)){
            return null;
        }
        CustomerEntity entity = CustomerMapper.parseDTOToEntity(dto);
        CustomerEntity customerCur = cusRepo.save(entity);
        return CustomerMapper.parseEntityToDTO(customerCur);
    }
}
