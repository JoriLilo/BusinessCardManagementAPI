package com.example.BusinessCardManagement.service;


import com.example.BusinessCardManagement.entity.Card;
import com.example.BusinessCardManagement.entity.Employee;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CardService {

    Card createCard(Employee employee);
    Optional<Card> getCardById(Long id);
    List<Card> getCardsByEmployeeId(Long employeeId); //employee

    Card freezeCard(Long id);
    Card cancelCard(Long id);

    Card updateSpendingLimit(Long id, BigDecimal spendingLimit);

    void validateCardPIN(Long cardId, String pin); // throw if invalid





}
