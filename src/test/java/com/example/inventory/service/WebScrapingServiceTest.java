package com.example.inventory.service;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class WebScrapingServiceTest {

    @Autowired
    private WebScrapingService webScrapingService;

    @Test
    void testExtractTitle() {
        String url = "https://www.oracle.com";
        String title = webScrapingService.extractTitle(url);
        assertThat(title).containsIgnoringCase("Oracle"); // adjust as needed
    }

    @Test
    void scrapeSauceDemoInventoryToJson_createsJsonFileWithItems() throws Exception {
        String filePath = "target/saucedemo_items_test.json";
        File jsonFile = new File(filePath);
        if (jsonFile.exists()) {
            jsonFile.delete();
        }

        webScrapingService.scrapeSauceDemoInventoryToJson(filePath);

        assertThat(jsonFile.exists())
                .as("JSON file should exist after scraping")
                .isTrue();

        assertThat(jsonFile.length())
                .as("JSON file should not be empty")
                .isGreaterThan(10);

        // Optional: check file parses as a list and the items have required fields
        ObjectMapper mapper = new ObjectMapper();
        List<?> items = mapper.readValue(jsonFile, List.class);
        assertThat(items).isNotEmpty();
        if (!items.isEmpty() && items.get(0) instanceof Map) {
            Map item0 = (Map) items.get(0);
            assertThat(item0).containsKeys("name", "description", "price", "imageUrl");
        }
    }

}
