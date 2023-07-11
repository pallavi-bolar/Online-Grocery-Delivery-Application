package com.axis.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.axis.entity.Customer;
import com.axis.exception.NotFoundException;
import com.axis.repository.CustomerRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RestController
@RequestMapping("/customers")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {

	@Autowired
    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
    
    @PostMapping("/create")
    public Customer createCustomer(@RequestBody Customer customer) {
      // Set the shoppingCart to null since it's not provided in the frontend
      customer.setShoppingCart(null);
      customer.setDelivery_address(null);
      return customerRepository.save(customer);
    }

    @GetMapping("id/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found with id: " + id));
    }

    @PutMapping("update/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found with id: " + id));

        existingCustomer.setName(updatedCustomer.getName());
        existingCustomer.setEmail(updatedCustomer.getEmail());
        existingCustomer.setPhone(updatedCustomer.getPhone());
        existingCustomer.setDelivery_address(updatedCustomer.getDelivery_address());

        return customerRepository.save(existingCustomer);
    }

    @DeleteMapping("delete/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerRepository.deleteById(id);
    }
    
    @GetMapping("/email/{email}")
    public ResponseEntity<Customer> getCustomerByEmail(@PathVariable String email) {
        Customer customer = customerRepository.findByEmail(email);
        if (customer != null) {
            return ResponseEntity.ok(customer);
        } else {
            throw new NotFoundException("Customer not found with email: " + email);
        }
    }
}
