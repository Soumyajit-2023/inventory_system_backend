package com.example.inventory.service;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.File;

@Service
public class WebScrapingService {

    public String extractTitle(String url) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        try {
            driver.get(url);
            return driver.getTitle();
        } finally {
            driver.quit();
        }
    }

    // Scrape all items from saucedemo.com (login required) and output to JSON file
    public void scrapeSauceDemoInventoryToJson(String outputFilePath) throws Exception {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://www.saucedemo.com/");
            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();

            // Wait for inventory page
            WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(5));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("inventory_item")));

            List<WebElement> itemElements = driver.findElements(By.className("inventory_item"));
            List<Map<String, Object>> items = new ArrayList<>();

            for (WebElement item : itemElements) {
                Map<String, Object> itemMap = new HashMap<>();
                // Get item name, price, description, image URL
                String name = item.findElement(By.className("inventory_item_name")).getText();
                String desc = item.findElement(By.className("inventory_item_desc")).getText();
                String price = item.findElement(By.className("inventory_item_price")).getText();
                String imgUrl = item.findElement(By.cssSelector("img.inventory_item_img")).getAttribute("src");
                itemMap.put("name", name);
                itemMap.put("description", desc);
                itemMap.put("price", price);
                itemMap.put("imageUrl", imgUrl);
                items.add(itemMap);
            }

            // Write to JSON
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(outputFilePath), items);
        } finally {
            driver.quit();
        }
    }
}
