package com.nashtech.hanashop.repository;

import com.nashtech.hanashop.data.entity.CustomerEntity;
import com.nashtech.hanashop.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity,Long> {
    CustomerEntity findByUser(UserEntity user);
}
