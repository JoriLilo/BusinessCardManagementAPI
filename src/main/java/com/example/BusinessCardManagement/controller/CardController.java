package com.example.BusinessCardManagement.controller;


import com.example.BusinessCardManagement.dto.CardDetailsResponseDto;
import com.example.BusinessCardManagement.dto.CardIssueRequestDto;
import com.example.BusinessCardManagement.dto.CardStatusUpdateDto;
import com.example.BusinessCardManagement.dto.EmployeeResponseDto;
import com.example.BusinessCardManagement.entity.Card;
import com.example.BusinessCardManagement.entity.Employee;
import com.example.BusinessCardManagement.service.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/Card")
public class CardController {

    public final CardService cardService;
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    public ResponseEntity<CardDetailsResponseDto> create(@PathVariable CardIssueRequestDto requestDto) {

        Card saved =cardService.createCard(new Employee());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CardDetailsResponseDto.fromEntitty(saved));


    }

    @GetMapping("/{id}")
    public CardDetailsResponseDto get(@PathVariable Long id) {

        Card found= cardService.getCardById(id)
                .orElseThrow(()->new RuntimeException("Card not found"));
        return CardDetailsResponseDto.fromEntitty(found);




    }

    @PutMapping("/cards/{id}/freeze}")
    public  ResponseEntity<CardDetailsResponseDto> freez(@PathVariable Long id, @RequestBody CardStatusUpdateDto requestDto) {
        Card activated = cardService.freezeCard(id);
        return ResponseEntity.ok(CardDetailsResponseDto.fromEntitty(activated));

    }
    @PutMapping("/cards/{id}/cancel")
    public  ResponseEntity<CardDetailsResponseDto> cancel (@PathVariable Long id) {
        Card activated = cardService.freezeCard(id);
        return ResponseEntity.ok(CardDetailsResponseDto.fromEntitty(activated));

    }
}
