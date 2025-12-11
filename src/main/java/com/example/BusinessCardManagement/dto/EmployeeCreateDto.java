package com.example.BusinessCardManagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

@Data
public class EmployeeCreateDto {

    @NotNull
    private String name;



    @Email
    @NotNull
    private String email;
}
