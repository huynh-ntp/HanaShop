package com.nashtech.hanashop.service.Impl;

import com.nashtech.hanashop.data.entity.RoleEntity;
import com.nashtech.hanashop.repository.RoleRepository;
import com.nashtech.hanashop.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepo;

    public RoleEntity findRole(String roleID){
        RoleEntity entity = roleRepo.findByRoleID(roleID);
        return entity;
    }
}
