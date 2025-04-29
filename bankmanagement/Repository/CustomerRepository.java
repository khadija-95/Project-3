package com.example.bankmanagement.Repository;

import com.example.bankmanagement.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Customer findCustomerByUserId(Integer userId);

    Customer findCustomerById(Integer id);


    List<Customer> findAllByUser_Id(Integer userId);
}
