package com.nashtech.hanashop.controller;

import com.nashtech.hanashop.data.dto.OrderDetailDTO;
import com.nashtech.hanashop.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderDetailController {

    @Autowired
    OrderDetailService orderDetailService;
    @GetMapping("/detail/{orderID}")
    @PreAuthorize("hasAnyRole('CUS')")
    public ResponseEntity getDetail(@PathVariable String orderID){
        List<OrderDetailDTO> listDTO = orderDetailService.findByOrder(orderID);
        if(listDTO.size()>0){
            return ResponseEntity.ok(listDTO);
        }
        return ResponseEntity.ok("Not thing");
    }

}
