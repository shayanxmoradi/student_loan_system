package org.example.services.card;

import org.example.entities.Card;
import org.example.services.baseentity.BaseEntityService;

import java.util.List;

public interface CardService extends BaseEntityService<Card,Long> {
    List<Card> findUsercards(Long userId);
}
