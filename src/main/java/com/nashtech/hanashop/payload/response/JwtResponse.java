package com.nashtech.hanashop.payload.response;

import lombok.Data;

import java.util.List;
@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String username;
    private String fullName;
    private String email;
    private String role;

    public JwtResponse(String accessToken, String username,String fullName, String email, String role) {
        this.token = accessToken;
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.role = role;
    }


}