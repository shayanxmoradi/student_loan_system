package org.example.menu.registerloan;

import org.example.entities.Card;
import org.example.entities.Student;
import org.example.entities.enums.BankType;
import org.example.menu.util.CardInfoCollector;
import org.example.menu.util.Input;
import org.example.menu.util.Message;
import org.example.services.card.CardService;
import org.example.util.AuthHolder;

import java.sql.Date;

public class CardPage {
    private final Input INPUT;
    private final Message MESSAGE;
    private final AuthHolder AUTH_HOLDER;
    private final CardService CARD_SERVICE;

    public CardPage(Input INPUT, Message message, AuthHolder authHolder, CardService cardService) {
        this.INPUT = INPUT;
        MESSAGE = message;
        AUTH_HOLDER = authHolder;
        CARD_SERVICE = cardService;
    }

    //todo
    Card show() {
      cardWhile:  while (true) {
            System.out.println("""
                    1 -> Chose between already created cards
                    2 -> Create new card
                    3 -> Cansel Operation
                    """);
            switch (INPUT.scanner.next()) {
                case "1" -> {
                   return  choseBetweenCards();
                }
                case "2" -> {
                    return createNewCard();
                }
                case "3" -> {
                    return null;
                }
                default -> System.out.println("Invalid choice");

            }
        }

    }

    private Card createNewCard() {
        Student student = AUTH_HOLDER.student;
        Card newCard = new Card();
        CardInfoCollector.collectCardInfo(newCard);
        newCard.setStudent(student);
        CARD_SERVICE.save(newCard);
        return newCard;
    }



    private Card choseBetweenCards() {
        return null; //todo
    }






}
