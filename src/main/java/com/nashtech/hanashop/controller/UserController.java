package com.nashtech.hanashop.controller;

import com.nashtech.hanashop.data.dto.ErrorPasswordChange;
import com.nashtech.hanashop.data.dto.ErrorRegisterDTO;
import com.nashtech.hanashop.data.dto.PasswordChangeDTO;
import com.nashtech.hanashop.data.dto.UserDTO;
import com.nashtech.hanashop.repository.UserRepository;
import com.nashtech.hanashop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@Controller
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepo;


    @GetMapping()
    @PreAuthorize("hasRole('AD')")
    public ResponseEntity<?> getAll(){
     List<UserDTO> listUsers = userService.getAll();
     return ResponseEntity.ok(listUsers);
    }


    @GetMapping("/{userName}")
    @PreAuthorize("hasAnyRole('CUS','AD')")
    public ResponseEntity<?> getUserInfo(@PathVariable("userName") String userName){
        UserDTO user = userService.getUser(userName);
        if(user==null){
            return ResponseEntity
                    .badRequest().body("User not found.");
        }
        return ResponseEntity.ok(user);
    }


    @DeleteMapping("/{userName}")
    @PreAuthorize("hasRole('AD')")
    public ResponseEntity deleteUser(@PathVariable("userName") String userName){
        String status= userService.deleteUser(userName);
        return ResponseEntity.ok(status);
    }

    @PutMapping()
    @PreAuthorize("hasAnyRole('CUS','AD')")
    public ResponseEntity updateUser(@RequestBody UserDTO dto){
        ErrorRegisterDTO error = validDataUpdate(dto);
        if(!error.getUserNameError().isEmpty() || !error.getPasswordError().isEmpty()
                || !error.getFullNameError().isEmpty()
                || !error.getEmailError().isEmpty()){
            return ResponseEntity
                    .badRequest()
                    .body(error);
        }
        return ResponseEntity.ok(userService.updateUser(dto));
    }

    @PutMapping("/password")
    @PreAuthorize("hasAnyRole('AD','CUS')")
    public ResponseEntity<?> changePass(@RequestBody PasswordChangeDTO dto){
        ErrorPasswordChange error = validPasswordChange(dto);
        if(!error.getUserNameError().isEmpty() || !error.getNewPasswordError().isEmpty()    ){
            return ResponseEntity
                    .badRequest()
                    .body(error);
        }
        UserDTO user = userService.getUser(dto.getUserName());
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(dto.getNewPass()));
        return ResponseEntity.ok(userService.updateUser(user));

    }

    private ErrorPasswordChange validPasswordChange(PasswordChangeDTO dto){
        ErrorPasswordChange error = new ErrorPasswordChange("", "");

        if(!userRepo.existsByUserName(dto.getUserName())){
            error.setUserNameError("Username not found");
            return error;
        }

        if(dto.getNewPass()==null || dto.getNewPass().trim().isEmpty()){
            error.setNewPasswordError("Error: Password it not empty");
        }
        if(dto.getNewPass().length()<=6){
            error.setNewPasswordError("Error: Password is too weak, at least 7 letter");
        }
        return error;
    }


    private ErrorRegisterDTO validDataUpdate(UserDTO dto){
        ErrorRegisterDTO error = new ErrorRegisterDTO("","","","");
        if(dto.getUserName()==null || dto.getUserName().trim().isEmpty()){
            error.setUserNameError("Error: Username is not empty");
        }
        if(dto.getPassword()==null || dto.getPassword().trim().isEmpty()){
            error.setPasswordError("Error: Password it not empty");
        }
        if(dto.getPassword().length()<=6){
            error.setPasswordError("Error: Password is too weak, at least 7 letter");

        }
        if(dto.getFullName() == null || dto.getFullName().trim().isEmpty()){
            error.setFullNameError("Error: Full name is not empty");
        }
        if(dto.getEmail()==null || dto.getEmail().trim().isEmpty()){
            error.setEmailError("Error: Email is not empty");
        }
        if(!dto.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")){
            error.setEmailError("Error: Invalid email address");
        }
        return error;
    }

}
