package com.example.BusinessCardManagement.service;

import com.example.BusinessCardManagement.entity.Employee;
import com.example.BusinessCardManagement.repository.EmployeeRepository;

import java.util.Optional;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee(Employee employee) {

        if (employee == null) {
            //throw exception
        }

        if(employee.getEmail().equals(employeeRepository.findByEmail(employee.getEmail()))) {
            //change the email.
        }

        if (employee.getStatus() == null) {
            employee.setStatus(Employee.Status.ACTIVE);
        }

        if (employee.getRole() == null) {
            employee.setRole(Employee.Role.EMPLOYEE);
        }

        return employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        if(employeeRepository.findById(id).isPresent()){
            return employeeRepository.findById(id);
        }else
             return  Optional.empty();
    }

    @Override
    public Employee activateEmployee(Long id) {
        Optional<Employee> employee=employeeRepository.findById(id);
        if(employee.isPresent()){
            employee.get().setStatus(Employee.Status.ACTIVE);
            return employeeRepository.save(employee.get());
        }
        return null; //will call an exeption if possible
    }

    @Override
    public Optional<Employee> deactivateEmployee(Long id) {

        Optional<Employee> employee=employeeRepository.findById(id);

        if(employee.isEmpty()){
            return Optional.empty();

        }else {
            Employee em= employee.get(); //get the employee
                   em.setStatus(Employee.Status.INACTIVE);
            employeeRepository.save(em);
            return Optional.of(em);
        }




    }

    @Override
    public Optional<Employee> findByEmail(String email) {
        if(employeeRepository.findByEmail(email).isPresent()){
            return employeeRepository.findByEmail(email);
        }else
            return Optional.empty();
    }
}
