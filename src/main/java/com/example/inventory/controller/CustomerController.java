package com.example.inventory.controller;

import com.example.inventory.entity.Customer;
import com.example.inventory.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<Customer> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        System.out.println(customers);
        return customers;
    }

    @PostMapping
    public Customer addCustomer(@RequestBody Customer customer) {
        System.err.println(customer);
        return customerService.saveCustomer(customer);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }
}
