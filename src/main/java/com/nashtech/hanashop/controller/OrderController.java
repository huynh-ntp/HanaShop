package com.nashtech.hanashop.controller;

import com.nashtech.hanashop.data.dto.CartDTO;
import com.nashtech.hanashop.data.dto.OrderDTO;
import com.nashtech.hanashop.data.dto.ProductDTO;
import com.nashtech.hanashop.service.OrderDetailService;
import com.nashtech.hanashop.service.OrderService;
import com.nashtech.hanashop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;

    @Autowired
    OrderDetailService orderDetailService;

    @PreAuthorize("hasRole('CUS')")
    @GetMapping("/history/{userName}")
    public ResponseEntity getByUserName(@PathVariable String userName){
        List<OrderDTO> listOrder = orderService.getAllOrderByUser(userName);
        if(listOrder.size()>0){
            return ResponseEntity.ok(listOrder);
        }
        return ResponseEntity.ok("Don't have any order");
    }

    @PostMapping()
    @PreAuthorize("hasRole('CUS')")
    public ResponseEntity<?> order(@RequestBody CartDTO cart){
        List<String> statusProduct = checkAvaiable(cart);
        if(statusProduct.size()>0){
            return ResponseEntity
                    .badRequest()
                    .body(statusProduct);
        }
        OrderDTO order = orderService.order(cart);
        return ResponseEntity
                .ok(order);
    }

    // Check quantity remain
    private List<String> checkAvaiable(CartDTO cart){
        List<String> list = new ArrayList<>();
        for(Map.Entry entry: cart.getCart().entrySet()){
            ProductDTO product = productService.findByProductID((String)entry.getKey());
            int remain = product.getQuantity();
            ProductDTO productBuy =(ProductDTO) entry.getValue();
            if(remain<productBuy.getQuantity()){
                list.add((String)entry.getKey()+ " don't have enough product!" );
            }
        }
        return list;
    }




}
