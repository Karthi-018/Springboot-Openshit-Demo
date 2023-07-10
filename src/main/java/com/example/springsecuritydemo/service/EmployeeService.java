package com.example.springsecuritydemo.service;

import com.example.springsecuritydemo.dao.Employee;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class EmployeeService {

    List<Employee> employeeList = null;

    @PostConstruct
    public void loadEmployee()
    {
        employeeList = IntStream.rangeClosed(1,10)
                .mapToObj(i->Employee.builder()
                        .empID(i)
                        .empName("Employee "+i)
                        .salary(new Random().nextDouble(25000)).build()
                ).collect(Collectors.toList());
    }

    public List<Employee> getEmployees()
    {
        return employeeList;
    }

    public Employee getEmployee(int id)
    {
        return employeeList.stream()
                .filter(employee -> employee.getEmpID()==id)
                .findAny()
                .orElseThrow(()->new RuntimeException("Employee "+id+" not Found"));
    }
}
