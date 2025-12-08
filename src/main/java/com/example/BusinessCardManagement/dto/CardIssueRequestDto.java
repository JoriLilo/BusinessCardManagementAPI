package com.example.BusinessCardManagement.dto;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class CardIssueRequestDto {

    @NotNull(message = "Account ID is mandatory for card issuance.")
    private Long accountId; // The ID of the BusinessAccount this card belongs to

    @NotBlank(message = "Cardholder name cannot be blank.")
    private String cardholderName;

    @NotNull(message = "Initial spending limit is required.")
    @DecimalMin(value = "100.00", inclusive = true, message = "The spending limit must be at least 100.00.")
    private BigDecimal spendingLimit; // The individual limit for this card

    @NotBlank(message = "Card type (e.g., VIRTUAL, PHYSICAL) must be specified.")
    private String cardType;


}
