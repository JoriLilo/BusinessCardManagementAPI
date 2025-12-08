package com.example.BusinessCardManagement.repository;

import com.example.BusinessCardManagement.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    List<Card> findByEmployeeId(Long id);

    Optional<Card> findByCardNumber(String cardNumber);

}
