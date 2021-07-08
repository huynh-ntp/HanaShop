package com.nashtech.hanashop.controller;

import com.nashtech.hanashop.data.dto.OrderDTO;
import com.nashtech.hanashop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;
    @PreAuthorize("hasRole('CUS')")
    @GetMapping("/history/{userName}")
    public ResponseEntity getByUserName(@PathVariable String userName){
        List<OrderDTO> listOrder = orderService.getAllOrderByUser(userName);
        if(listOrder.size()>0){
            return ResponseEntity.ok(listOrder);
        }
        return ResponseEntity.ok("Don't have any order");

    }
}
