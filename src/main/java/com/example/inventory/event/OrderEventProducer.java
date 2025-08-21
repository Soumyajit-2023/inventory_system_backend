package com.example.inventory.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderEventProducer {
    private static final String TOPIC = "order-events";

    @Autowired
    private KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public void publishOrderEvent(OrderEvent orderEvent) {
        kafkaTemplate.send(TOPIC, String.valueOf(orderEvent.getOrderId()), orderEvent);
    }
}
