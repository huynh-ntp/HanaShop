package com.nashtech.hanashop.repository;

import com.nashtech.hanashop.data.entity.CategoryEntity;
import com.nashtech.hanashop.data.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
    ProductEntity findByProductID(String productID);
    List<ProductEntity> findByCategory(CategoryEntity category);
    List<ProductEntity> findByProductNameContains(String productName);
    List<ProductEntity> findByProductNameContainsAndCategory(String productName, CategoryEntity categoryEntity);
}
