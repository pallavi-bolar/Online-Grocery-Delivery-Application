package com.axis.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.axis.entity.Customer;
import com.axis.repository.CustomerRepository;

@RestController
public class LoginController {

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/api/login")
    public ResponseEntity<Customer> login(@RequestBody Customer customer) {
        String email = customer.getEmail();
        String password = customer.getPassword();
        
        Customer existingCustomer = customerRepository.findByEmail(email);

        return ResponseEntity.ok(existingCustomer);
    }

}
