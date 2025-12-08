package com.example.BusinessCardManagement.service;

import com.example.BusinessCardManagement.entity.Employee;

import java.util.Optional;

public interface EmployeeService {


    Employee createEmployee(Employee employee);
    Optional<Employee> getEmployeeById(Long id);
    Employee activateEmployee(Long id);
    Optional<Employee> deactivateEmployee(Long id);
    Optional<Employee> findByEmail(String email);





}
