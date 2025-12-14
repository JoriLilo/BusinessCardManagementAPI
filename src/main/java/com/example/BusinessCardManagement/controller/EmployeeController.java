package com.example.BusinessCardManagement.controller;

import com.example.BusinessCardManagement.dto.EmployeeCreateDto;
import com.example.BusinessCardManagement.dto.EmployeeResponseDto;
import com.example.BusinessCardManagement.entity.Employee;
import com.example.BusinessCardManagement.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeResponseDto> create(@RequestBody @Valid EmployeeCreateDto dto) {

        Employee saved = employeeService.createEmployee(dto.toEntity());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(EmployeeResponseDto.fromEntity(Optional.ofNullable(saved)));
    }

    @GetMapping("{id}")
    public EmployeeResponseDto get(@PathVariable Long id) {
        Employee found= employeeService.getEmployeeById(id)
                .orElseThrow(()->new RuntimeException("Employee not found"));

        return EmployeeResponseDto.fromEntity(Optional.ofNullable(found));

    }

    @PutMapping("/{id}/activate")
    public ResponseEntity<EmployeeResponseDto> activateEmployee(@PathVariable Long id) {

        Employee activated = employeeService.activateEmployee(id);
        return ResponseEntity.ok(EmployeeResponseDto.fromEntity(Optional.ofNullable(activated)));

    }



}
