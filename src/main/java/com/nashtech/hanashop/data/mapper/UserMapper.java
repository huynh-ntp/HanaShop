package com.nashtech.hanashop.data.mapper;

import com.nashtech.hanashop.data.dto.UserDTO;
import com.nashtech.hanashop.data.entity.RoleEntity;
import com.nashtech.hanashop.data.entity.UserEntity;


public class UserMapper {
    public static UserDTO parseEntityToDTO(UserEntity entity){
        UserDTO dto = new UserDTO();
        dto.setUserName(entity.getUserName());
        dto.setPassword(entity.getPassword());
        dto.setFullName(entity.getFullName());
        dto.setEmail(entity.getEmail());
        dto.setRoleID(entity.getRole().getRoleID());
        dto.setStatus(entity.isStatus());
        return dto;
    }

    public static UserEntity parseDTOToEntity(UserDTO dto){
        UserEntity entity = new UserEntity();
        entity.setUserName(dto.getUserName());
        entity.setPassword(dto.getPassword());
        entity.setFullName(dto.getFullName());
        entity.setEmail(dto.getEmail());
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleID(dto.getRoleID());
        entity.setRole(roleEntity);
        entity.setStatus(dto.isStatus());
        return entity;
    }
}
