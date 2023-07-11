package com.axis.service;

import java.time.LocalDateTime;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axis.entity.Customer;
import com.axis.entity.Order;
import com.axis.entity.OrderItem;
import com.axis.entity.ShoppingCart;
import com.axis.repository.OrderRepository;
import com.axis.repository.ShoppingCartRepository;

@Service
public class OrderService {
	
	@Autowired
    private final OrderRepository orderRepository;
    private final ShoppingCartRepository shoppingCartRepository;

    public OrderService(OrderRepository orderRepository, ShoppingCartRepository shoppingCartRepository) {
        this.orderRepository = orderRepository;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public void placeOrder(Long cartId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(cartId).orElse(null);
        Long customerId = shoppingCart.getCustomer().getId();
      
        // Create a new order
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setShoppingCart(shoppingCart);
     // Set the customer on the order
        Customer customer = new Customer();
        customer.setId(customerId);
        order.setCustomer(customer);

        // Convert cart items to order items
        List<OrderItem> orderItems = shoppingCart.getCartItems().stream()
                .map(cartItem -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrder(order);
                    orderItem.setProduct(cartItem.getProduct());
                    orderItem.setQuantity(cartItem.getQuantity());
                    return orderItem;
                })
                .collect(Collectors.toList());

        order.setOrderItems(orderItems);

        // Save the order
        orderRepository.save(order);

        // Clear the shopping cart
        shoppingCart.getCartItems().clear();
        shoppingCartRepository.save(shoppingCart);
    }
    
    public List<Order> getOrdersByCustomerId(Long customerId) {
        return orderRepository.findByCustomer_Id(customerId);
    }
    
    public List<Order> getOrderHistory(Long customerId) {
        return orderRepository.findByCustomer_Id(customerId);
    }
}
