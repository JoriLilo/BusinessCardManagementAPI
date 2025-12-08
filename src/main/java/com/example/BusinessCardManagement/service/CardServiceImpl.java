package com.example.BusinessCardManagement.service;

import com.example.BusinessCardManagement.entity.Card;
import com.example.BusinessCardManagement.entity.Employee;
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
        card.setCardNumber(generateCardNumber());
        card.setExpiryDate(LocalDate.now().plusYears(5));
        card.setEmployee(employee);
       // handle later  card.setPinHash(hashPin(pin));
        card.setStatus(Card.CardStatus.ACTIVE);
        card.setMonthlyLimit(new BigDecimal("1000")); // example


        return cardRepository.save(card);
    }

    private String generateCardNumber() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 16; i++){
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
                //will throw an exception
            }else
                card1.setStatus(Card.CardStatus.FROZEN);
        }//else throw an exception
        return cardRepository.save(card.get());
    }

    @Override
    public Card cancelCard(Long id) {
        Optional<Card> card=cardRepository.findById(id);

        if(card.isPresent()){
            Card card1=card.get();
            if(card1.getStatus().equals(Card.CardStatus.CANCELLED)){
                //will throw an exception
            }else
                card1.setStatus(Card.CardStatus.CANCELLED);
        }//else throw an exception
        return cardRepository.save(card.get());
    }

    @Override
    public Card updateSpendingLimit(Long id, BigDecimal spendingLimit) {
        Optional<Card> card=cardRepository.findById(id);
        if(card.isPresent()){
            Card card1=card.get();
            card1.setMonthlyLimit(spendingLimit);
        }
        return cardRepository.save(card.get());
    }

    @Override
    public void validateCardPIN(Long cardId, String pin) {


        Optional<Card> card=cardRepository.findById(cardId);
        if(card.isPresent()){
            Card card1=card.get();

            if(card1.getPinHash().equals(pin)){
                card1.setStatus(Card.CardStatus.ACTIVE);
            }else if(nrOfWrongPin<3){
                nrOfWrongPin++;
                System.out.println("Invalid pin");
            }else
                card1.setStatus(Card.CardStatus.BLOCKED);
        }


    }
}
