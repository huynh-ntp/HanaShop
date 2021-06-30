package com.nashtech.hanashop.data.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String userName;
    private String password;
    private String fullName;
    private String roleID;
    private String email;
    private boolean status;

}
