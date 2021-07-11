package com.nashtech.hanashop.data.dto;

import lombok.Data;

@Data
public class ErrorRegisterDTO {
    private String userNameError;
    private String passwordError;
    private String fullNameError;
    private String emailError;

    public ErrorRegisterDTO(String userNameError, String passwordError, String fullNameError, String emailError) {
        this.userNameError = userNameError;
        this.passwordError = passwordError;
        this.fullNameError = fullNameError;
        this.emailError = emailError;
    }
}
