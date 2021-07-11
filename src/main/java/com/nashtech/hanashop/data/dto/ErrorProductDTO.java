package com.nashtech.hanashop.data.dto;

import lombok.Data;

import java.util.Date;
@Data
public class ErrorProductDTO {
    private String productIDError;
    private String productNameError;
    private String imageSrcError;
    private String descriptionError;
    private String priceError;
    private String quantityError;
    private String categoryIDError;

    public ErrorProductDTO(String productIDError, String productNameError,
                           String imageSrcError, String descriptionError,
                           String priceError, String quantityError, String categoryIDError) {
        this.productIDError = productIDError;
        this.productNameError = productNameError;
        this.imageSrcError = imageSrcError;
        this.descriptionError = descriptionError;
        this.priceError = priceError;
        this.quantityError = quantityError;
        this.categoryIDError = categoryIDError;
    }
}
