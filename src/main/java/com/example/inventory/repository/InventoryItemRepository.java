package com.example.inventory.repository;

import com.example.inventory.entity.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
    InventoryItem findByName(String name);
}
