package com.example.springsecuritydemo.controller;

import com.example.springsecuritydemo.dao.Employee;
import com.example.springsecuritydemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("welcome")
    public String welcomeHome()
    {
//        employeeService.loadEmployee();
        return "Welcome to Employee Management System";
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Employee> getAllEmployees()
    {
        return employeeService.getEmployees();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Employee getEmployee(@PathVariable int id)
    {
        return employeeService.getEmployee(id);
    }
}
