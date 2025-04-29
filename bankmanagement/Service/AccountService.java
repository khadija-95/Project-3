package com.example.bankmanagement.Service;

import com.example.bankmanagement.Api.ApiException;
import com.example.bankmanagement.Model.Account;
import com.example.bankmanagement.Model.Customer;
import com.example.bankmanagement.Repository.AccountRepository;
import com.example.bankmanagement.Repository.CustomerRepository;
import com.example.bankmanagement.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    public void createAccount(Integer userId, String accountNumber) {
        Customer customer = customerRepository.findById(userId).orElseThrow(() -> new ApiException("Customer not found"));
        Account account = new Account();
                account.setAccountNumber(accountNumber);
                account.setBalance(0.0);
                account.setIsActive(false);
                account.setCustomer(customer);
        accountRepository.save(account);
    }

    public void activateAccount(Integer userId, Integer accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new ApiException("Account not found"));
        account.setIsActive(true);
        accountRepository.save(account);
    }

    public List<Account> getAccountsByCustomer(Integer userId) {
        Customer customer = customerRepository.findById(userId).orElseThrow(() -> new RuntimeException("Customer not found"));
        return accountRepository.findAllByCustomer(customer);
    }

    public Account getAccountDetails(Integer userId, String accountNumber) {
        Account account = accountRepository.findAByAccountNumber(accountNumber);
        if (account == null) throw new ApiException("Account not found");
        return account;
    }

    public void deposit(Integer userId, String accountNumber, Double amount) {
        Account account = accountRepository.findAByAccountNumber(accountNumber);
        if (account == null) throw new ApiException("Account not found");

        if (account.getCustomer().getUser().getId()!= userId){
            throw new ApiException("Unauthorized");
        }
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
    }

    public void withdraw(Integer userId, String accountNumber, Double amount) {
        Account account = accountRepository.findAByAccountNumber(accountNumber);
        if (account == null) throw new ApiException("Account not found");
        if (account.getCustomer().getUser().getId()!= userId){
            throw new ApiException("Unauthorized");
        }
        if (account.getBalance() < amount) throw new ApiException("Insufficient balance");
        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);
    }

    public void transfer(Integer userId, String fromAccountNumber, String toAccountNumber, Double amount) {
        Account fromAccount = accountRepository.findAByAccountNumber(fromAccountNumber);
        Account toAccount = accountRepository.findAByAccountNumber(toAccountNumber);

        if (fromAccount == null || toAccount == null) throw new ApiException("Account not found");
        if (fromAccount.getCustomer().getUser().getId()!= userId){
            throw new ApiException("Unauthorized");
        }
        if (fromAccount.getBalance() < amount) throw new ApiException("Insufficient balance");

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }

    public void blockAccount(Integer userId, Integer accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new ApiException("Account not found"));
        if (account.getCustomer().getUser().getId()!= userId){
            throw new ApiException("Unauthorized");
        }
        account.setIsActive(false);
        accountRepository.save(account);
    }
}
