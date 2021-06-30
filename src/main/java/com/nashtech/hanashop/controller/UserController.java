package com.nashtech.hanashop.controller;

import com.nashtech.hanashop.data.dto.UserDTO;
import com.nashtech.hanashop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/user")
    public ResponseEntity getAll(){
     List<UserDTO> listUsers = userService.getAll();
     return ResponseEntity.ok(listUsers);
    }
    @PostMapping("/user")
    public ResponseEntity createUser(@RequestBody UserDTO dto1){
        UserDTO dto = userService.createUser(dto1);
        if(dto == null){
            return ResponseEntity.ok("Exist userName");
        }
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/user/{userName}")
    public ResponseEntity deleteUser(@PathVariable("userName") String userName){
        String status= userService.deleteUser(userName);
        return ResponseEntity.ok(status);
    }

    @PutMapping("/user")
    public ResponseEntity updateUser(@RequestBody UserDTO dot){
        return ResponseEntity.ok(userService.updateUser(dot));
    }
}
