package com.nashtech.hanashop.repository;

import com.nashtech.hanashop.data.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
    ProductEntity findByProductID(String productID);



}
