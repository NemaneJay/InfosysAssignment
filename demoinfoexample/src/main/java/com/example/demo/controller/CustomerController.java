package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.Customer;
import com.example.demo.service.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/customers")
@Tag(name = "Customer Controller" , description = "This is customer controller")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    
    @PostMapping("/register")
    @Operation(summary = "Registers new customer",description = "this is customer api")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200" , description = "Success (OK)"),
    		@ApiResponse(responseCode = "401" , description = "not authorized "),
    })
    public Customer registerCustomer(@RequestParam String userName) {
        return customerService.registerCustomer(userName);
    }

    @GetMapping("/getAll")
    @Operation(summary = "Gives all customers",description = "this is customer api")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200" , description = "Success (OK)"),
    		@ApiResponse(responseCode = "401" , description = "not authorized "),
    })
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Gives particular customer",description = "this is customer api")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200" , description = "Success (OK)"),
    		@ApiResponse(responseCode = "401" , description = "not authorized "),
    })
    public Customer getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }
}
