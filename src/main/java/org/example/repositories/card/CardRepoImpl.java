package org.example.repositories.card;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.entities.Card;
import org.example.entities.loan.Loan;
import org.example.repositories.baseentity.BaseEntityRepoImpl;
import org.example.util.Utilties;

import java.util.List;

public class CardRepoImpl extends BaseEntityRepoImpl<Card,Long> implements CardRepo {
    public CardRepoImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Card> getEntityClass() {
        return Card.class;
    }

    @Override
    public List<Card> findUserCards(Long userId) {

        TypedQuery<Card> query = entityManager.createQuery(
                "SELECT c FROM Card c WHERE c.student.id= :student_id", Card.class);
        //todo finding paramter here ??
        query.setParameter("student_id", userId);

        return query.getResultStream().toList();
    }
}
