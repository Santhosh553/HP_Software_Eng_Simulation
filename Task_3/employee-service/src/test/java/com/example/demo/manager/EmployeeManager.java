package com.example.demo.manager;

import com.example.demo.model.Employee;
import com.example.demo.model.Employees;

public class EmployeeManager {
    private Employees employees;

    public EmployeeManager() {
        employees = new Employees();
        employees.getEmployeeList().add(new Employee("1", "John", "Doe", "john.doe@example.com", "Developer"));
        employees.getEmployeeList().add(new Employee("2", "Jane", "Smith", "jane.smith@example.com", "Manager"));
        employees.getEmployeeList().add(new Employee("3", "Jim", "Brown", "jim.brown@example.com", "Analyst"));
    }

    public Employees getAllEmployees() {
        return employees;
    }

    public void addEmployee(Employee employee) {
        employees.getEmployeeList().add(employee);
    }
}
