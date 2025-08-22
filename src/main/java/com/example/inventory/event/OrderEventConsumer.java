package com.example.inventory.event;

import com.example.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderEventConsumer {

    @Autowired
    private InventoryService inventoryService;

    @KafkaListener(topics = "order-events", groupId = "inventory-group", containerFactory = "orderEventKafkaListenerContainerFactory")
    public void consumeOrderEvent(OrderEvent orderEvent) {
        // Only decrease stock for PLACED orders
        if ("PLACED".equals(orderEvent.getStatus())) {
            inventoryService.decreaseStock(orderEvent.getItemId(), orderEvent.getQuantity());
        }
        // For "REJECTED" or others, ignore
    }
}
