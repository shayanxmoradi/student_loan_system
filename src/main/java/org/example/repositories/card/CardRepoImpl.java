package org.example.repositories.card;

import jakarta.persistence.EntityManager;
import org.example.entities.Card;
import org.example.repositories.baseentity.BaseEntityRepoImpl;

public class CardRepoImpl extends BaseEntityRepoImpl<Card,Long> implements CardRepo {
    public CardRepoImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Card> getEntityClass() {
        return Card.class;
    }
}
