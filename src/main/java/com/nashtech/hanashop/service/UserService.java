package com.nashtech.hanashop.service;

import com.nashtech.hanashop.data.dto.PasswordChangeDTO;
import com.nashtech.hanashop.data.dto.UserDTO;
import com.nashtech.hanashop.data.entity.UserEntity;
import sun.security.util.Password;

import java.util.List;

public interface UserService {
    List<UserDTO> getAll();
//    UserDTO createUser(UserDTO dto);
    String deleteUser(String userName);
    UserDTO updateUser(UserDTO dto);
    UserDTO getUser(String userName);
    UserDTO changePassword(PasswordChangeDTO dto);
}
