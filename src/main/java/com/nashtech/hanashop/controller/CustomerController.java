package com.nashtech.hanashop.controller;

import com.nashtech.hanashop.data.dto.CustomerDTO;
import com.nashtech.hanashop.data.mapper.CustomerMapper;
import com.nashtech.hanashop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/{userName}")
    public ResponseEntity<?> getCustomerByUserName(@PathVariable("userName") String userName){
        CustomerDTO cusDTO = customerService.findByUserName(userName);
        if(cusDTO!=null){
            return ResponseEntity
                    .ok()
                    .body(cusDTO);
        }
        return ResponseEntity
                .badRequest()
                .body("Customer not found by this userName");
    }
    @PostMapping("/updateCusomer")
    public ResponseEntity<?> updateCustomer(@RequestBody CustomerDTO dto){
        CustomerDTO newCus = customerService.updateCustomer(dto);
        if(newCus==null){
            return ResponseEntity
                    .badRequest()
                    .body("Customer not exist");
        }
        return ResponseEntity
                .ok()
                .body(newCus);
    }

}
