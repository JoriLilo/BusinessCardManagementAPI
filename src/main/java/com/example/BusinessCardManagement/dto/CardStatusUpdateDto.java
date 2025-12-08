package com.example.BusinessCardManagement.dto;


import com.example.BusinessCardManagement.entity.Card;
import lombok.Builder;
import lombok.Data;

@Data
@Builder //allows object creation
public class CardStatusUpdateDto {

    private Long id;
    private String cardNumberMasked;

    private String message;

    public static CardStatusUpdateDto fromEntity(Card card) {

        return CardStatusUpdateDto.builder()
                .id(card.getId())

                .cardNumberMasked("XXXX-XXXX-XXXX-" + card.getCardNumber().substring(12))
                .message("Card status successfully updated to: " + card.getStatus().name())
                .build();

    }






}
