package com.nashtech.hanashop.controller;

import com.nashtech.hanashop.data.dto.UserDTO;
import com.nashtech.hanashop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;


    @GetMapping()
    @PreAuthorize("hasRole('AD')")
    public ResponseEntity getAll(){
     List<UserDTO> listUsers = userService.getAll();
     return ResponseEntity.ok(listUsers);
    }

    @DeleteMapping("/{userName}")
    @PreAuthorize("hasRole('AD')")
    public ResponseEntity deleteUser(@PathVariable("userName") String userName){
        String status= userService.deleteUser(userName);
        return ResponseEntity.ok(status);
    }

    @PutMapping()
    @PreAuthorize("hasAnyRole('AD','USER')")
    public ResponseEntity updateUser(@RequestBody UserDTO dot){
        return ResponseEntity.ok(userService.updateUser(dot));
    }



}
