package com.example.inventory.controller;

import com.example.inventory.entity.Customer;
import com.example.inventory.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class CustomerControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        customerRepository.deleteAll();
    }

    @Test
    void testGetCustomers_returnsList() throws Exception {
        Customer c1 = new Customer("A", "a@example.com", "testPassword1");
        Customer c2 = new Customer("B", "b@example.com", "testPassword2");
        customerRepository.save(c1);
        customerRepository.save(c2);

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[?(@.name == 'A')]").exists())
                .andExpect(jsonPath("$[?(@.name == 'B')]").exists())
                .andExpect(jsonPath("$[?(@.email == 'a@example.com')]").exists())
                .andExpect(jsonPath("$[?(@.email == 'b@example.com')]").exists());
    }

    @Test
    void testPostCustomers_createsCustomer() throws Exception {
        String body = "{\"name\":\"NewUser\", \"email\":\"newuser@example.com\", \"password\":\"newUserPass\"}";
        mockMvc.perform(post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("NewUser"))
                .andExpect(jsonPath("$.email").value("newuser@example.com"));
    }
}
