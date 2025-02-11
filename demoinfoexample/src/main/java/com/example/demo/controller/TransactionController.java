package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.TransactionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/transactions")
@Tag(name = "Transaction Controller" , description = "This is transaction controller")
public class TransactionController {
	@Autowired
    private TransactionService transactionService;

	@PostMapping("/add")
	 @Operation(summary = "add new transaction",description = "this is add-transaction api")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200" , description = "Success (OK)"),
    		@ApiResponse(responseCode = "401" , description = "not authorized "),
    		@ApiResponse(responseCode = "500" , description = "Customer not found")
    })
    public RewardPointsResponse addTransaction(@RequestBody TransactionRequest transactionRequest) {
        return transactionService.addTransaction(transactionRequest.getCustomerId(), transactionRequest.getAmount(), transactionRequest.getDate());
    }
    
    @DeleteMapping("/delete/{customerId}")
    @Operation(summary = "delete the transaction",description = "this is delete-transaction api")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200" , description = "Success (OK)"),
    		@ApiResponse(responseCode = "401" , description = "not authorized "),
    		@ApiResponse(responseCode = "500" , description = "Customer not found")
    })
    public String deleteTransaction(@PathVariable Long customerId) {
        transactionService.deleteTransaction(customerId);
		return "Deleted";
    }
    
    @GetMapping("/rewards/monthly/{customerId}")
    @Operation(summary = "Get monthly rewards for particular customer",description = "this is get-transaction api")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200" , description = "Success (OK)"),
    		@ApiResponse(responseCode = "401" , description = "not authorized "),
    		@ApiResponse(responseCode = "500" , description = "Customer not found")
    })
    public RewardPointsResponse getMonthlyRewardPoints(@PathVariable Long customerId) {
        return transactionService.calculateMonthlyRewardPoints(customerId);
    }
    
    @GetMapping("/customers/points")
    @Operation(summary = "Get monthly rewards for all customers",description = "this is getAll-transaction api")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200" , description = "Success (OK)"),
    		@ApiResponse(responseCode = "401" , description = "not authorized "),
    })
    public List<TransactionDetailsResponse> getAllCustomersWithPoints() {
        return transactionService.getAllCustomersWithPoints();
    }
    
    @PatchMapping("/customers/edit")
    @Operation(summary = "Edit transaction for particular customer",description = "this is edit-transaction api")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200" , description = "Success (OK)"),
    		@ApiResponse(responseCode = "401" , description = "not authorized "),
    		@ApiResponse(responseCode = "500" , description = "Customer not found")
    })
    public RewardPointsResponse editTransaction(@RequestBody TransactionRequest transactionRequest) {
    	return transactionService.editTransaction(transactionRequest.getCustomerId(), transactionRequest.getAmount(), transactionRequest.getDate());
    	
    }
}
