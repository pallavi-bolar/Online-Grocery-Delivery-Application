package com.axis.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.axis.entity.Order;
import com.axis.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/place-order/{cartId}")
    public ResponseEntity<String> placeOrder(@PathVariable("cartId") Long cartId) {
        try {
            orderService.placeOrder(cartId);
            return ResponseEntity.ok("Order placed successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to place order: " + e.getMessage());
        }
    }
    
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Order>> getOrdersByCustomerId(@PathVariable Long customerId) {
        List<Order> orders = orderService.getOrdersByCustomerId(customerId);
        if (!orders.isEmpty()) {
            return ResponseEntity.ok(orders);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
    
    @GetMapping("/get-order-history/{customerId}")
    public ResponseEntity<List<Order>> getOrderHistory(@PathVariable Long customerId) {
        List<Order> orderHistory = orderService.getOrderHistory(customerId);
        return ResponseEntity.ok(orderHistory);
    }
}
