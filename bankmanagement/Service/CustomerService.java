package com.example.bankmanagement.Service;

import com.example.bankmanagement.Api.ApiException;
import com.example.bankmanagement.DTO.CustomerDTO;
import com.example.bankmanagement.Model.Customer;
import com.example.bankmanagement.Model.User;
import com.example.bankmanagement.Repository.CustomerRepository;
import com.example.bankmanagement.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    public void addCustomer( Integer userId,CustomerDTO customerDTO) {
        User user = userRepository.findUserById(userId);
        if (user==null){
            throw new ApiException("User not found");
        }

        if (!user.getRole().equals("CUSTOMER")) {
            throw new ApiException("User is not assigned as CUSTOMER");
        }

        Customer customer = new Customer();
                customer.setPhoneNumber(customerDTO.getPhoneNumber());
                customer.setUser(user);

        customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers(Integer userId) {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Integer customerId) {
        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer==null){
            throw new ApiException("Customer not found");
        }
              return customer;
    }

    public void updateCustomer(Integer userId,Integer customerId, CustomerDTO customerDTO) {
        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer==null){
            throw new ApiException("Customer not found");
        }

        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customerRepository.save(customer);
    }

    public void deleteCustomer(Integer userId,Integer customerId) {
        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer==null){
            throw new ApiException("Customer not found");
        }

        customerRepository.delete(customer);
    }
}
