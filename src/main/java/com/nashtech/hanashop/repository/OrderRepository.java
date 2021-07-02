package com.nashtech.hanashop.repository;

import com.nashtech.hanashop.data.entity.OrderEntity;
import com.nashtech.hanashop.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity,Long> {
    List<OrderEntity> findByUser(UserEntity user);
}
