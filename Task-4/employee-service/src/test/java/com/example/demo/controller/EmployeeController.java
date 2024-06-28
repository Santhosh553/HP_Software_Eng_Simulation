package com.example.demo.controller;

import com.example.demo.manager.EmployeeManager;
import com.example.demo.model.Employee;
import com.example.demo.model.Employees;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeManager employeeManager;

    public EmployeeController() {
        employeeManager = new EmployeeManager();
    }

    @GetMapping
    public Employees getAllEmployees() {
        return employeeManager.getAllEmployees();
    }

    @PostMapping
    public ResponseEntity<String> addEmployee(@RequestBody Employee employee) {
        employeeManager.addEmployee(employee);
        return new ResponseEntity<>("Employee added successfully", HttpStatus.CREATED);
    }
}
