package com.nashtech.hanashop.controller;

import com.nashtech.hanashop.data.dto.RateDTO;
import com.nashtech.hanashop.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/rate")
public class RateController  {
    @Autowired
    RateService rateService;

    @GetMapping("/{productID}")
    public ResponseEntity getByProduct(@PathVariable String productID){
        List<RateDTO> listRate = rateService.findByProductID(productID);
        if(listRate.size()>0){
            return ResponseEntity.ok(listRate);
        }
        return ResponseEntity
                .badRequest().body("Don't have any rate for this product");
    }

    @PostMapping("/user/rating")
    @PreAuthorize("hasRole('CUS')")
    public ResponseEntity<?> rating(@RequestBody RateDTO rateDTO) {
        RateDTO dto = rateService.rating(rateDTO);
        return ResponseEntity.ok(dto);
    }

}
