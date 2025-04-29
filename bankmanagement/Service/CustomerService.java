package com.example.bankmanagement.Service;

import com.example.bankmanagement.Api.ApiException;
import com.example.bankmanagement.DTO.CustomerDTO;
import com.example.bankmanagement.Model.Customer;
import com.example.bankmanagement.Model.User;
import com.example.bankmanagement.Repository.CustomerRepository;
import com.example.bankmanagement.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    public void registerCustomer(CustomerDTO customerDTO) {
        User user = new User();
        user.setName(customerDTO.getName());
        user.setUsername(customerDTO.getUsername());
        user.setEmail(customerDTO.getEmail());
        user.setRole("CUSTOMER");

        String hashPassword = new BCryptPasswordEncoder().encode(customerDTO.getPassword());
        user.setPassword(hashPassword);

        userRepository.save(user);


        Customer customer = new Customer();
                customer.setPhoneNumber(customerDTO.getPhoneNumber());
                customer.setUser(user);

        customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers(Integer userId) {
        return customerRepository.findAllByUser_Id(userId);
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
        if (customer.getUser().getId()!=userId){
            throw new ApiException("Unauthorized");
        }

        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customerRepository.save(customer);
    }

    public void deleteCustomer(Integer userId,Integer customerId) {
        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer==null){
            throw new ApiException("Customer not found");
        }
        if (customer.getUser().getId()!=userId){
            throw new ApiException("Unauthorized");
        }

        customerRepository.delete(customer);
    }
}
