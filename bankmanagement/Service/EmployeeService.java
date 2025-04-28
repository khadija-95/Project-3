package com.example.bankmanagement.Service;

import com.example.bankmanagement.Api.ApiException;
import com.example.bankmanagement.DTO.EmployeeDTO;
import com.example.bankmanagement.Model.Employee;
import com.example.bankmanagement.Model.User;
import com.example.bankmanagement.Repository.EmployeeRepository;
import com.example.bankmanagement.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;

    public void addEmployee(Integer userId ,EmployeeDTO employeeDTO) {
        User user = userRepository.findUserById(userId);
        if (user==null){
            throw new ApiException("User not found");
        }

        if (!user.getRole().equals("EMPLOYEE")) {
            throw new ApiException("User not assigned as EMPLOYEE");
        }

        Employee employee = new Employee();
                employee.setPosition(employeeDTO.getPosition());
                employee.setSalary(employeeDTO.getSalary());
                employee.setUser(user);


        employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees(Integer userId) {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Integer employeeId) {
        Employee employee = employeeRepository.findEmployeeById(employeeId);
        if (employee == null) {
            throw new ApiException("Employee not found");
        }
        return employee;
    }


    public void updateEmployee(Integer userId,Integer employeeId, EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.findEmployeeById(employeeId);
        if (employee==null){
            throw new ApiException("Employee not found");
        }

        employee.setPosition(employeeDTO.getPosition());
        employee.setSalary(employeeDTO.getSalary());

        employeeRepository.save(employee);
    }

    public void deleteEmployee(Integer userId,Integer employeeId) {
        Employee employee = employeeRepository.findEmployeeById(employeeId);
        if (employee==null){
            throw new ApiException("Employee not found");
        }

        employeeRepository.delete(employee);
    }
}
