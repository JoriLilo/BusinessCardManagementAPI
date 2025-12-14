package com.example.BusinessCardManagement.dto;

import com.example.BusinessCardManagement.entity.Card;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CardDetailsResponseDto {

    private Long id;
    private String cardNumberMasked;
    private String expiryDate;
    private String status;
    private BigDecimal monthlyLimit;
    private Long employeeId;
    private String employeeName;

    public static  CardDetailsResponseDto fromEntitty(Card card) {
        return CardDetailsResponseDto.builder()
                .id(card.getId())
                .cardNumberMasked("XXXX-XXXX-XXXX-" + card.getCardNumber().substring(12))
                .status(card.getStatus().name())
                .monthlyLimit(card.getMonthlyLimit())
                .expiryDate(card.getExpiryDate().toString())
                .employeeId(card.getEmployee().getId())
                .employeeName(card.getEmployee().getName())
                .build();
    }


}
