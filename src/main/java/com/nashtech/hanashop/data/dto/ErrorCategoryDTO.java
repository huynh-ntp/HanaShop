package com.nashtech.hanashop.data.dto;

import lombok.Data;

@Data
public class ErrorCategoryDTO {
    private String categoryIDError;
    private String categoryNameError;

    public ErrorCategoryDTO(String categoryIDError, String categoryNameError) {
        this.categoryIDError = categoryIDError;
        this.categoryNameError = categoryNameError;
    }
}
