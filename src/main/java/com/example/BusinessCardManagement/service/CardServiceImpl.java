package com.example.BusinessCardManagement.service;

import com.example.BusinessCardManagement.entity.Card;
import com.example.BusinessCardManagement.entity.Employee;
import com.example.BusinessCardManagement.exceptions.InvalidRequestException;
import com.example.BusinessCardManagement.exceptions.ResourceNotFoundException;
import com.example.BusinessCardManagement.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    int nrOfWrongPin = 0;
    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;

    }


    @Override
    public Card createCard(Employee employee) {
        if (employee == null) {
            return null;
        }
        Card card = new Card();
        card.setCardNumber(generateCardNumber(16));
        card.setExpiryDate(LocalDate.now().plusYears(5));
        card.setEmployee(employee);
        card.setPin(generateCardNumber(4));
        card.setStatus(Card.CardStatus.ACTIVE);
        card.setMonthlyLimit(new BigDecimal("1000")); // example
        cardRepository.save(card);

        return card;
    }

    private String generateCardNumber(int n) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++){
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    @Override
    public Optional<Card> getCardById(Long id) {

        return cardRepository.findById(id);
    }

    @Override
    public List<Card> getCardsByEmployeeId(Long employeeId) {

        return cardRepository.findByEmployeeId(employeeId);
    }

    @Override
    public Card freezeCard(Long id) {

        Optional<Card> card=cardRepository.findById(id);

        if(card.isPresent()){
            Card card1=card.get();
            if(card1.getStatus().equals(Card.CardStatus.FROZEN)){
               throw new InvalidRequestException("Card is FROZEN");

            }else {
                card1.setStatus(Card.CardStatus.FROZEN);
                cardRepository.save(card1);
                return card1;
            }
        }else
            throw new ResourceNotFoundException("card does not exist");



    }

    @Override
    public Card cancelCard(Long id) {
        Optional<Card> card=cardRepository.findById(id);

        if(card.isPresent()){
            Card card1=card.get();
            if(card1.getStatus().equals(Card.CardStatus.CANCELLED)){
                throw new InvalidRequestException("Card is CANCELLED");
            }else
                card1.setStatus(Card.CardStatus.CANCELLED);
            cardRepository.save(card1);
            return card1;
        }else
            throw new ResourceNotFoundException("card does not exist");
    }

    @Override
    public Card updateSpendingLimit(Long id, BigDecimal spendingLimit) {
        Optional<Card> card=cardRepository.findById(id);
        if(card.isPresent()){
            Card card1=card.get();
            if(spendingLimit.compareTo(BigDecimal.ZERO)<=0){
                throw new InvalidRequestException("Spending limit must be greater than zero");
            }else {
                card1.setMonthlyLimit(spendingLimit);
                cardRepository.save(card1);
                return card1;

            }
        }
        throw new ResourceNotFoundException("card does not exist");

    }

    @Override
    public void validateCardPIN(Long cardId, String pin) {


        Optional<Card> card = cardRepository.findById(cardId);
        if (card.isPresent()) {
            Card card1 = card.get();

            if (card1.getPin().equals(pin)) {
                card1.setStatus(Card.CardStatus.ACTIVE);
            } else {
                card1.setStatus(Card.CardStatus.BLOCKED);
                throw new InvalidRequestException("Wrong Pin, card is being blocked");

            }
            cardRepository.save(card1);


        }
        throw new ResourceNotFoundException("card does not exist");
    }
}
