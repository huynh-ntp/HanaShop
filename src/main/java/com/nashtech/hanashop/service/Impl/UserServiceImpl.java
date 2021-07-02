package com.nashtech.hanashop.service.Impl;

import com.nashtech.hanashop.data.dto.UserDTO;
import com.nashtech.hanashop.data.entity.UserEntity;
import com.nashtech.hanashop.data.mapper.UserMapper;
import com.nashtech.hanashop.repository.RoleRepository;
import com.nashtech.hanashop.repository.UserRepository;
import com.nashtech.hanashop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepo;
    @Autowired
    RoleRepository roleRepo;
    @Override
    public List<UserDTO> getAll(){
        List<UserEntity> listUserEntity = userRepo.findAll();
        List<UserDTO> listUserDTO = new ArrayList<>();
        listUserEntity.forEach(entity -> {
            listUserDTO.add(UserMapper.parseEntityToDTO(entity));
        });
        return listUserDTO;
    };


    @Override
    public UserDTO createUser(UserDTO userDTO){
        if(userRepo.existsByUserName(userDTO.getUserName())){
            return null;
        }
        UserEntity entity = UserMapper.parseDTOToEntity(userDTO);
        userRepo.save(entity);
        return UserMapper.parseEntityToDTO(entity);
    }

    @Override
    public String deleteUser(String userName){
        UserEntity entity = userRepo.findByUserName(userName);
        if(entity!=null){
            userRepo.delete(entity);
            return "Delete success";
        }
        return "Delete failed";
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO){
        UserEntity userEntity = UserMapper.parseDTOToEntity(userDTO);
        userRepo.save(userEntity);
        return userDTO;
    }


}