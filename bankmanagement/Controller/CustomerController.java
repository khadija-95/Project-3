package com.example.bankmanagement.Controller;

import com.example.bankmanagement.DTO.CustomerDTO;
import com.example.bankmanagement.Model.User;
import com.example.bankmanagement.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@RequestBody @Valid CustomerDTO customerDTO) {
        customerService.registerCustomer( customerDTO);
        return ResponseEntity.status(201).body("Customer added successfully");
    }

    @GetMapping("/get")
    public ResponseEntity getAllCustomers(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(customerService.getAllCustomers(user.getId()));
    }

    @PutMapping("/update/{customerId}")
    public ResponseEntity<String> updateCustomer(@AuthenticationPrincipal User user, @PathVariable Integer customerId, @RequestBody @Valid CustomerDTO customerDTO) {
        customerService.updateCustomer(user.getId(), customerId, customerDTO);
        return ResponseEntity.ok("Customer updated successfully");
    }

    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity<String> deleteCustomer(@AuthenticationPrincipal User user, @PathVariable Integer customerId) {
        customerService.deleteCustomer(user.getId(), customerId);
        return ResponseEntity.ok("Customer deleted successfully");
    }
}
