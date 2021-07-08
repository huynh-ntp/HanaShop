package com.nashtech.hanashop.controller;

import com.nashtech.hanashop.data.dto.UserDTO;
import com.nashtech.hanashop.data.mapper.UserMapper;
import com.nashtech.hanashop.payload.response.JwtResponse;
import com.nashtech.hanashop.repository.UserRepository;
import com.nashtech.hanashop.security.jwt.JwtUtils;
import com.nashtech.hanashop.security.services.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;



import java.util.List;
import java.util.stream.Collectors;


@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/account")
@RestController
public class AuthController {


    final private AuthenticationManager authenticationManager;

    final private UserRepository userRepository;


    final private PasswordEncoder encoder;

    final private JwtUtils jwtUtils;

    public AuthController (AuthenticationManager authenticationManager, UserRepository userRepository,
                            PasswordEncoder encoder, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }



    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody UserDTO user) {

        // TODO, authenticate when login
        // Username, pass from client
        // com.nashtech.rookies.security.WebSecurityConfig.configure(org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder)
//        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        // on this step, we tell to authenticationManager how we load data from database
        // and the password encoder

        if(user.getUserName()==null || user.getUserName().trim().isEmpty()
                || user.getPassword()==null
                || user.getPassword().trim().isEmpty()
        ) return ResponseEntity.badRequest().body("Error: Username and password must be fill");

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));

        // if go there, the user/password is correct
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // generate jwt to return to client

        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(
                jwt,
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles
        ));
        

    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
        if (userRepository.existsByUserName(userDTO.getUserName())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Username is already taken!");
        }
        String validStatus = validData(userDTO);
        if(!validStatus.equals("Valid")){
            return ResponseEntity
                    .badRequest()
                    .body(validStatus);
        }

        UserDTO user = new UserDTO();
        user.setUserName(userDTO.getUserName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(encoder.encode(userDTO.getPassword()));
        user.setRoleID("CUS");
        user.setFullName(userDTO.getFullName());
        user.setStatus(true);

        userRepository.save(UserMapper.parseDTOToEntity(user));
        return ResponseEntity.ok(user);
    }

    private String validData(UserDTO dto){
        if(dto.getUserName()==null || dto.getUserName().trim().isEmpty()){
            return "Error: Username is not empty";
        }
        if(dto.getPassword()==null || dto.getPassword().trim().isEmpty()){
            return "Error: Password it not empty";
        }
        if(dto.getPassword().length()<=6){
            return "Error: Password is too weak";
        }
        if(dto.getFullName() == null || dto.getFullName().trim().isEmpty()){
            return "Error: Full name is not empty";
        }
        if(dto.getEmail()==null || dto.getEmail().trim().isEmpty()){
            return "Error: Email is not empty";
        }
        if(!dto.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")){
            return "Error: Invalid email address";
        }
        return "Valid";
    }
}
