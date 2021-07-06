package com.nashtech.hanashop.controller;

import com.nashtech.hanashop.data.dto.RateDTO;
import com.nashtech.hanashop.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RateController  {
    @Autowired
    RateService rateService;
    @GetMapping("/rate/{productID}")
    public ResponseEntity getByProduct(@PathVariable String productID){
        List<RateDTO> listRate = rateService.findByProductID(productID);
        if(listRate.size()>0){
            return ResponseEntity.ok(listRate);
        }
        return ResponseEntity.ok("Don't have any rate for this product");
    }


}
