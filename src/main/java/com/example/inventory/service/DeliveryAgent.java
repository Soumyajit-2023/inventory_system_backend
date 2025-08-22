package com.example.inventory.service;

import com.example.inventory.entity.Order;
import com.example.inventory.event.OrderEvent;
import com.example.inventory.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeliveryAgent {

    @Autowired
    private OrderRepository orderRepository;

    @KafkaListener(topics = "order-events", groupId = "delivery-agent-group", containerFactory = "orderEventKafkaListenerContainerFactory")
    public void onOrderEvent(OrderEvent event) {
        if ("PLACED".equals(event.getStatus()) && !event.isOrderDelivered()) {
            // Simulate delivery: update the order as delivered
            Optional<Order> orderOpt = orderRepository.findById(event.getOrderId());
            orderOpt.ifPresent(order -> {
                order.setOrderDelivered(true);
                orderRepository.save(order);
                // Optionally, log or handle post-delivery actions here
            });

            System.out.println("Order deliverd by DeliveryAgent");
        }
        // Ignore REJECTED or already delivered orders
    }

}
