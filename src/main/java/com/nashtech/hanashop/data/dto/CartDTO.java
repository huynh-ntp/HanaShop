package com.nashtech.hanashop.data.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class CartDTO {
    private Map<String,ProductDTO> cart;

    public void add(ProductDTO dto){
        if(cart==null){
            cart = new HashMap<>();
        }
        if(cart.containsKey(dto.getProductID())){
            int quantity = cart.get(dto.getProductID()).getQuantity();
            dto.setQuantity(quantity+1);
        }
        cart.put(dto.getProductID(),dto);
    }

    public void update(int quantity,String productID){
        if(cart==null) return;
        if(cart.containsKey(productID)){
            cart.get(productID).setQuantity(quantity);
        }
    }

    public void delete(String productID){
        if(cart==null) return;
        if(cart.containsKey(productID)){
            cart.remove(productID);
        }
    }



}
