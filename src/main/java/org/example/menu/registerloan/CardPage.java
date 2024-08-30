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
import java.util.List;
import java.util.stream.IntStream;

public class CardPage {
    private final Input INPUT;
    private final Message MESSAGE;
    private final AuthHolder AUTH_HOLDER;
    private final CardService CARD_SERVICE;
    List<Card> userCards;

    public CardPage(Input INPUT, Message message, AuthHolder authHolder, CardService cardService) {
        this.INPUT = INPUT;
        MESSAGE = message;
        AUTH_HOLDER = authHolder;
        CARD_SERVICE = cardService;
    }

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
       userCards = CARD_SERVICE.findUsercards(AUTH_HOLDER.student.getId());
       if (userCards.isEmpty()) {
           System.out.println("You dont have any cards, try to add new card");
           return createNewCard();
       }
        IntStream.range(0, userCards.size())
                .forEach(i -> System.out.println((i + 1) + ": " + userCards.get(i)));
        int chosedCardNumber= getValidInt();

        return userCards.get(chosedCardNumber-1); //todo

    }



    public  int getValidInt() {
        while (true) {
            System.out.print("Please enter a number: ");
            if (INPUT.scanner.hasNextInt()) {
                int resulat =  INPUT.scanner.nextInt();
                if (resulat > 0 && resulat <= userCards.size()) {
                    return resulat;
                }else {
                    System.out.println("not in valid range");
                }
            } else {
                System.out.println("here you are allowed just give numbers nothing else. Please try again.");
                INPUT.scanner.next(); // discard the invalid input
            }
        }
    }


}
