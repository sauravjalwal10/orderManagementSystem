package com.example.oms.services;


import com.example.oms.entities.Order;
import com.example.oms.entities.Inventory;
import com.example.oms.repository.OrderRepository;
import com.example.oms.repository.InventoryRepository;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Transactional
    public Order placeOrder(Order order) {
        Inventory inventory = inventoryRepository.findById(order.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (inventory.getQuantity() < order.getQuantity()) {
            throw new RuntimeException("Insufficient inventory");
        }

        // Reduce inventory count
        inventory.setQuantity(inventory.getQuantity() - order.getQuantity());
        inventoryRepository.save(inventory);

        order.setStatus("PENDING");
        order.setCreatedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }

    public Order updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        order.setUpdatedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }
}

