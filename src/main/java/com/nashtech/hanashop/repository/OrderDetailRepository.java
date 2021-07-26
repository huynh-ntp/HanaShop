package com.nashtech.hanashop.repository;

import com.nashtech.hanashop.data.entity.OrderDetailEntity;
import com.nashtech.hanashop.data.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity,Long> {
    List<OrderDetailEntity> findByOrder(OrderEntity order);

}

