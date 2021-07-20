package com.controllerTest;

import com.nashtech.hanashop.controller.UserController;
import com.nashtech.hanashop.data.dto.UserDTO;
import com.nashtech.hanashop.service.UserService;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(UserController.class)
@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;

    private UserService userService = Mockito.mock(UserService.class);

    private List<UserDTO> list = new ArrayList<>();
    UserDTO user = new UserDTO();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        list.add(user);
    }

    @Test
    public void getAll_ReturnStatus200AndJsonArray() throws Exception{
        Mockito.when(userService.getAll()).thenReturn(list);
        RequestBuilder request = MockMvcRequestBuilders.get("/api/user");
        MvcResult result = mvc.perform(request).andReturn();
        Assertions.assertEquals(200,result.getResponse().getStatus());
    }

}
