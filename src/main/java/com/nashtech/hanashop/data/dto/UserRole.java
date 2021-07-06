package com.nashtech.hanashop.data.dto;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;
import com.nashtech.hanashop.data.dto.*;

public enum UserRole {
    AD(Sets.newHashSet(
            UserPermisson.PRODUCT_READ,
            UserPermisson.PRODUCT_WRITE,
            UserPermisson.ORDER_READ,
            UserPermisson.RATE_READ,
            UserPermisson.ORDER_READ,
            UserPermisson.ORDER_DETAIL_READ
    )),
    CUS(Sets.newHashSet(
            UserPermisson.PRODUCT_READ,
            UserPermisson.RATE_READ,
            UserPermisson.RATE_WRITE,
            UserPermisson.ORDER_READ,
            UserPermisson.ORDER_WRITE,
            UserPermisson.ORDER_DETAIL_READ
    ));

    private final Set<UserPermisson> permissionSet;

    UserRole(Set<UserPermisson> permissons){
        this.permissionSet = permissons;
    }

    public Set<UserPermisson> getPermissions(){
        return permissionSet;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthority(){
        Set<SimpleGrantedAuthority> permissons =  getPermissions().stream()
                .map(permisson -> new SimpleGrantedAuthority(permisson.getPermisson()))
                .collect(Collectors.toSet());
        permissons.add(new SimpleGrantedAuthority("ROLE_"+ this.name()));
        return permissons;
    }
}
