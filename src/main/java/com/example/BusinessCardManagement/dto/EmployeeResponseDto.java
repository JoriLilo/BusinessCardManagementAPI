package com.example.BusinessCardManagement.dto;

import com.example.BusinessCardManagement.entity.Employee;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeResponseDto {

    private Long id;
    private String name;

    private String email;
    private Employee.Status status;
    private Employee.Role role;

    public static EmployeeResponseDto fromEntity(Employee employee) {

        return EmployeeResponseDto.builder()
                .id(employee.getId())
                .name(employee.getName())
                .email(employee.getEmail())
                .status(employee.getStatus())
                .role(employee.getRole())
                .build();

    }




}
