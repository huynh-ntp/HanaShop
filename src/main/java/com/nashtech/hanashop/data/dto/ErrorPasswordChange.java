package com.nashtech.hanashop.data.dto;

import lombok.Data;

@Data
public class ErrorPasswordChange {
    private String userNameError;
    private String newPasswordError;

    public ErrorPasswordChange(String userNameError, String newPasswordError) {
        this.userNameError = userNameError;
        this.newPasswordError = newPasswordError;
    }
}
