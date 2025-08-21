package com.example.inventory;

import com.example.inventory.entity.InventoryItem;
import com.example.inventory.repository.InventoryItemRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;
import java.math.BigDecimal;

@Component
public class InventoryInitializer {

    @Autowired
    private InventoryItemRepository inventoryItemRepository;

    @PostConstruct
    public void init() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream is = getClass()
                    .getClassLoader()
                    .getResourceAsStream("item-store/saucedemo_items_test.json");
            JsonNode itemsArray = objectMapper.readTree(is);

            for (JsonNode itemNode : itemsArray) {
                String name = itemNode.get("name").asText();
                String description = itemNode.has("description") ? itemNode.get("description").asText() : "";
                String priceStr = itemNode.has("price") ? itemNode.get("price").asText().replace("$", "") : "0";
                BigDecimal price = new BigDecimal(priceStr);
                String imageUrl = itemNode.has("imageUrl") ? itemNode.get("imageUrl").asText() : "";

                InventoryItem existing = inventoryItemRepository.findByName(name);
                if (existing != null) {
                    existing.setQuantity(100);
                    existing.setDescription(description);
                    existing.setPrice(price);
                    existing.setImageUrl(imageUrl);
                    inventoryItemRepository.save(existing);
                } else {
                    InventoryItem newItem = new InventoryItem(name, 100, description, price, imageUrl);
                    inventoryItemRepository.save(newItem);
                }
            }
        } catch (Exception e) {
            System.err.println("Failed to initialize inventory items: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
