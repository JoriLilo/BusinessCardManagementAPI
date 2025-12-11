package com.example.BusinessCardManagement.service;

import com.example.BusinessCardManagement.entity.Employee;
import com.example.BusinessCardManagement.exceptions.ResourceNotFoundException;
import com.example.BusinessCardManagement.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee(Employee employee) {

        if (employee == null) {
            throw new ResourceNotFoundException("Employee is NULL");
        }

        if(employee.getEmail().equals(employeeRepository.findByEmail(employee.getEmail()))) {

            throw new ResourceNotFoundException("Employee already exists");

        }

        if (employee.getStatus() == null) {
            employee.setStatus(Employee.Status.ACTIVE);
        }

        if (employee.getRole() == null) {
            employee.setRole(Employee.Role.EMPLOYEE);
        }
        employeeRepository.save(employee);

        return employee;
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {

            return employeeRepository.findById(id);

    }

    @Override
    public Employee activateEmployee(Long id) {
        Optional<Employee> employee=employeeRepository.findById(id);
        if(employee.isPresent()){
            employee.get().setStatus(Employee.Status.ACTIVE);
           employeeRepository.save(employee.get());
            return employee.get();
        }
       throw new ResourceNotFoundException("Employee not found");
    }

    @Override
    public Optional<Employee> deactivateEmployee(Long id) {

        Optional<Employee> employee=employeeRepository.findById(id);

        if(employee.isEmpty()){

            throw new ResourceNotFoundException("Employee not found");

        }else {
            Employee em= employee.get(); //get the employee
                   em.setStatus(Employee.Status.INACTIVE);
            employeeRepository.save(em);
            return Optional.of(em);
        }




    }

    @Override
    public Optional<Employee> findByEmail(String email) {

            return employeeRepository.findByEmail(email);


    }
}
