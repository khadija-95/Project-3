package com.example.bankmanagement.Controller;

import com.example.bankmanagement.Model.User;
import com.example.bankmanagement.Service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<String> createAccount(@AuthenticationPrincipal User user, @RequestParam String accountNumber) {
        accountService.createAccount(user.getId(), accountNumber);
        return ResponseEntity.status(201).body("Account created successfully");
    }

    @PutMapping("/activate/{accountId}")
    public ResponseEntity<String> activateAccount(@AuthenticationPrincipal User user, @PathVariable Integer accountId) {
        accountService.activateAccount(user.getId(), accountId);
        return ResponseEntity.ok("Account activated successfully");
    }

    @GetMapping("/list")
    public ResponseEntity listAccounts(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(accountService.getAccountsByCustomer(user.getId()));
    }

    @GetMapping("/details/{accountNumber}")
    public ResponseEntity accountDetails(@AuthenticationPrincipal User user, @PathVariable String accountNumber) {
        return ResponseEntity.ok(accountService.getAccountDetails(user.getId(), accountNumber));
    }

    @PutMapping("/deposit")
    public ResponseEntity<String> deposit(@AuthenticationPrincipal User user, @RequestParam String accountNumber, @RequestParam Double amount) {
        accountService.deposit(user.getId(), accountNumber, amount);
        return ResponseEntity.ok("Amount deposited successfully");
    }

    @PutMapping("/withdraw")
    public ResponseEntity<String> withdraw(@AuthenticationPrincipal User user, @RequestParam String accountNumber, @RequestParam Double amount) {
        accountService.withdraw(user.getId(), accountNumber, amount);
        return ResponseEntity.ok("Amount withdrawn successfully");
    }

    @PutMapping("/transfer")
    public ResponseEntity<String> transfer(@AuthenticationPrincipal User user, @RequestParam String fromAccount, @RequestParam String toAccount, @RequestParam Double amount) {
        accountService.transfer(user.getId(), fromAccount, toAccount, amount);
        return ResponseEntity.ok("Transfer completed successfully");
    }

    @PutMapping("/block/{accountId}")
    public ResponseEntity<String> blockAccount(@AuthenticationPrincipal User user, @PathVariable Integer accountId) {
        accountService.blockAccount(user.getId(), accountId);
        return ResponseEntity.ok("Account blocked successfully");
    }
}
