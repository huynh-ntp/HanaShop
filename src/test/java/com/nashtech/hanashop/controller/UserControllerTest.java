package com.nashtech.hanashop.controller;

import com.nashtech.hanashop.controller.UserController;
import com.nashtech.hanashop.data.dto.UserDTO;
import com.nashtech.hanashop.service.UserService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
//@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)


public class  UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    private List<UserDTO> userList;

    @Before
    public void init() {
        userList = Arrays.asList(new UserDTO(),new UserDTO(),new UserDTO());
    }

    @Test
    @WithMockUser(username="phihuynh")
    public void getAll_ReturnUserList() throws Exception{
        Mockito.when(userService.getAll()).thenReturn(userList);
        mockMvc.perform(get("/api/user").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}