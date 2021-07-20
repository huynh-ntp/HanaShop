package com.nashtech.hanashop.repository;

import com.nashtech.hanashop.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {


   UserEntity findByUserName(String userName);
   boolean existsByUserName(String userName);
}
