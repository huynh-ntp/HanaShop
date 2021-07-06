package com.nashtech.hanashop.data.dto;

public enum UserPermisson {
    PRODUCT_READ("product:read"),
    PRODUCT_WRITE("product:write"),
    RATE_READ("rate:read"),
    RATE_WRITE("rate:write"),
    ORDER_READ("order:read"),
    ORDER_WRITE("order:write"),
    ORDER_DETAIL_READ("order:read");

    private final String permisson;


    UserPermisson(String permisson) {
        this.permisson = permisson;
    }

    public String getPermisson(){
        return permisson;
    }

}
