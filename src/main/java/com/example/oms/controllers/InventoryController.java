package com.example.oms.controllers;

import com.example.oms.entities.Inventory;
import com.example.oms.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryRepository inventoryRepository;

    @PutMapping("/{productId}")
    public Inventory updateInventory(@PathVariable Long productId, @RequestParam int quantity) {
        Inventory inventory = inventoryRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        inventory.setQuantity(quantity);
        return inventoryRepository.save(inventory);
    }
}

