package com.servicetest;

import com.nashtech.hanashop.data.dto.UserDTO;
import com.nashtech.hanashop.data.entity.RoleEntity;
import com.nashtech.hanashop.data.entity.UserEntity;
import com.nashtech.hanashop.repository.UserRepository;
import com.nashtech.hanashop.service.Impl.UserServiceImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserServiceImpl userService;

    private UserEntity entity = new UserEntity();
    private List<UserEntity> list = new ArrayList<>();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        entity.setRole(new RoleEntity());
        list.add(entity);
    }

    @Test
    public void getAll_ReturnListUser(){
        Mockito.when(userRepository.findAll()).thenReturn(list);
        Assertions.assertEquals(1,userService.getAll().size());
    }
    @Test
    public void deleteUser_ReturnSuccessMessage(){
        Mockito.when(userRepository.findByUserName(Mockito.anyString())).thenReturn(entity);
        Assertions.assertEquals("Delete success",userService.deleteUser(Mockito.anyString()));
    }
    @Test
    public void deleteUser_ReturnFailedMessage(){
        Mockito.when(userRepository.findByUserName(Mockito.anyString())).thenReturn(null);
        Assertions.assertEquals("Delete failed, username not found!",
                userService.deleteUser(Mockito.anyString()));
    }
    
    @Test
    public void updateUser_ReturnUserDTO(){
        Mockito.when(userRepository.save(Mockito.anyObject())).thenReturn(entity);
        Assertions.assertNotNull(
                userService.updateUser(new UserDTO())
        );
    }
}
