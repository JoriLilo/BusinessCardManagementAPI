package com.example.BusinessCardManagement.dto;

import com.example.BusinessCardManagement.entity.Employee;
import lombok.Builder;
import lombok.Data;

import java.util.Optional;

@Data
@Builder
public class EmployeeResponseDto {

    private Long id;
    private String name;

    private String email;
    private Employee.Status status;
    private Employee.Role role;

    public static EmployeeResponseDto fromEntity(Optional<Employee> employee) {

        return EmployeeResponseDto.builder()
                .id(employee.get().getId())
                .name(employee.get().getName())
                .email(employee.get().getEmail())
                .status(employee.get().getStatus())
                .role(employee.get().getRole())
                .build();

    }




}
