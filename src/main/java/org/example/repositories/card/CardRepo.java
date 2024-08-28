package org.example.repositories.card;

import org.example.entities.Card;
import org.example.repositories.baseentity.BaseEntityRepo;

import java.util.List;

public interface CardRepo extends BaseEntityRepo<Card,Long> {
    List<Card> findUserCards(Long userId);
}
