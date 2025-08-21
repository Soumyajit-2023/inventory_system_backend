package com.example.inventory.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/item-store")
public class ItemStoreController {

    @GetMapping("/test-items")
    public List<Map<String, Object>> getTestItems() throws Exception {
        ClassPathResource resource = new ClassPathResource("item-store/saucedemo_items_test.json");
        try (InputStream inputStream = resource.getInputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(inputStream, new TypeReference<List<Map<String, Object>>>() {
            });
        }
    }
}
