package com.nashtech.hanashop.repository;

import com.nashtech.hanashop.data.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Long> {
    RoleEntity findByRoleID(String roleID);

}
