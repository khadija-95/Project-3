package com.example.bankmanagement.Controller;

import com.example.bankmanagement.DTO.EmployeeDTO;
import com.example.bankmanagement.Model.User;
import com.example.bankmanagement.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("/register")
    public ResponseEntity<String> registerEmployee( @RequestBody @Valid EmployeeDTO employeeDTO) {
        employeeService.registerEmployee(employeeDTO);
        return ResponseEntity.status(201).body("Employee added successfully");
    }

    @GetMapping("/get")
    public ResponseEntity getAllEmployees(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(employeeService.getAllEmployees(user.getId()));
    }

    @PutMapping("/update/{employeeId}")
    public ResponseEntity<String> updateEmployee(@AuthenticationPrincipal User user, @PathVariable Integer employeeId, @RequestBody @Valid EmployeeDTO employeeDTO) {
        employeeService.updateEmployee(user.getId(), employeeId, employeeDTO);
        return ResponseEntity.ok("Employee updated successfully");
    }

    @DeleteMapping("/delete/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@AuthenticationPrincipal User user, @PathVariable Integer employeeId) {
        employeeService.deleteEmployee(user.getId(), employeeId);
        return ResponseEntity.ok("Employee deleted successfully");
    }


}
