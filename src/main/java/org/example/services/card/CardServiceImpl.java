package org.example.services.card;

import org.example.entities.Card;
import org.example.repositories.card.CardRepo;
import org.example.services.baseentity.BaseEntityService;
import org.example.services.baseentity.BaseEntityServiceImp;

import java.util.List;

public class CardServiceImpl extends BaseEntityServiceImp<Card,Long, CardRepo> implements CardService {
   CardRepo cardRepo;
    public CardServiceImpl(CardRepo baseRepository) {
        super(baseRepository);
        cardRepo = baseRepository;
    }

    @Override
    public List<Card> findUsercards(Long userId) {
        return cardRepo.findUserCards(userId);
    }
}
