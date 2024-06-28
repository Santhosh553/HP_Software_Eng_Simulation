package com.example.demo.controller;

import com.example.demo.manager.EmployeeManager;
import com.example.demo.model.Employee;
import com.example.demo.model.Employees;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class EmployeeControllerTests {

    private MockMvc mockMvc;

    @Mock
    private EmployeeManager employeeManager;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    public void testGetAllEmployees() throws Exception {
        Employees employees = new Employees();
        employees.getEmployeeList().add(new Employee("1", "John", "Doe", "john.doe@example.com", "Developer"));
        employees.getEmployeeList().add(new Employee("2", "Jane", "Smith", "jane.smith@example.com", "Manager"));

        when(employeeManager.getAllEmployees()).thenReturn(employees);

        mockMvc.perform(MockMvcRequestBuilders.get("/employees")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.employeeList").exists());
    }

    @Test
    public void testAddEmployee() throws Exception {
        Employee newEmployee = new Employee("3", "Alice", "Johnson", "alice.johnson@example.com", "Engineer");

        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                .content(asJsonString(newEmployee))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        // Verify if the employee was added
        mockMvc.perform(MockMvcRequestBuilders.get("/employees")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.employeeList[*].employee_id").value("3"));
    }

    
    private String asJsonString(final Object obj) {
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
