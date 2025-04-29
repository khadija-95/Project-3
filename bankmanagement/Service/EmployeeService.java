package com.example.bankmanagement.Service;

import com.example.bankmanagement.Api.ApiException;
import com.example.bankmanagement.DTO.EmployeeDTO;
import com.example.bankmanagement.Model.Employee;
import com.example.bankmanagement.Model.User;
import com.example.bankmanagement.Repository.EmployeeRepository;
import com.example.bankmanagement.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;

    public void registerEmployee(EmployeeDTO employeeDTO) {
        User user = new User();
        user.setName(employeeDTO.getName());
        user.setUsername(employeeDTO.getUsername());
        user.setEmail(employeeDTO.getEmail());
        user.setRole("EMPLOYEE");

        String hashPassword = new BCryptPasswordEncoder().encode(employeeDTO.getPassword());
        user.setPassword(hashPassword);

        userRepository.save(user);

        Employee employee = new Employee();
                employee.setPosition(employeeDTO.getPosition());
                employee.setSalary(employeeDTO.getSalary());
                employee.setUser(user);


        employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees(Integer userId) {
        return employeeRepository.findAllByUser_Id(userId);
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
        if (employee.getUser().getId()!=userId){
            throw new ApiException("Unauthorized");
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
        if (employee.getUser().getId()!=userId){
            throw new ApiException("Unauthorized");
        }

        employeeRepository.delete(employee);
    }
}
