package com.nashtech.hanashop.repository;

import com.nashtech.hanashop.data.entity.ProductEntity;
import com.nashtech.hanashop.data.entity.RateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RateRepository extends JpaRepository<RateEntity,Long> {
    List<RateEntity> findByProduct(ProductEntity product);
}
