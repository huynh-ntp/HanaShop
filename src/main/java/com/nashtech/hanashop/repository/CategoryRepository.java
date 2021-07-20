package com.nashtech.hanashop.repository;

import com.nashtech.hanashop.data.entity.CategoryEntity;
import com.nashtech.hanashop.data.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity,Long> {
    boolean existsByCategoryID(String categoryID);
}
