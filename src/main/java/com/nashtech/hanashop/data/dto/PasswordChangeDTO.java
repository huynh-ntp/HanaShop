package com.nashtech.hanashop.data.dto;

import lombok.Data;

@Data
public class PasswordChangeDTO {
    private String userName;
    private String newPass;
}
