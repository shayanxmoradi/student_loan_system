package org.example.services.card;

import org.example.entities.Card;
import org.example.repositories.card.CardRepo;
import org.example.services.baseentity.BaseEntityService;
import org.example.services.baseentity.BaseEntityServiceImp;

public class CardServiceImpl extends BaseEntityServiceImp<Card,Long, CardRepo> implements CardService {
    public CardServiceImpl(CardRepo baseRepository) {
        super(baseRepository);
    }
}
