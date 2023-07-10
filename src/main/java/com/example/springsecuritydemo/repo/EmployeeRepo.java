package com.example.springsecuritydemo.repo;

import com.example.springsecuritydemo.dao.Employee;
import com.example.springsecuritydemo.dao.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<UserInfo,Integer> {
}
