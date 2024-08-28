package org.example.menu.registerloan;

import org.example.menu.util.Input;
import org.example.menu.util.Message;
import org.example.util.AuthHolder;

import java.math.BigDecimal;

public class StudyLoanPage {
    private final AuthHolder AUTH_HOLDER;
    private final Input INPUT;
    private final Message MESSAGE;

    public StudyLoanPage(AuthHolder authHolder, Input input, Message message) {
        AUTH_HOLDER = authHolder;
        INPUT = input;
        MESSAGE = message;
    }

    void show(){
        BigDecimal loanAmount= BigDecimal.valueOf(0);
        System.out.println("welcome To studyloan page");
        System.out.println("based on your Degree Type wich is " + AUTH_HOLDER.degreeType +
                           "you are allowed to take " + AUTH_HOLDER.degreeType.allowedLoanAmount() + " per Semester");

        System.out.println("do you will to take your "+AUTH_HOLDER.degreeType.allowedLoanAmount() + " loan ? [Y/N]");
        String wantsLoahn = INPUT.scanner.next().toLowerCase();

        if(wantsLoahn.equals("y")){
            // show owned cards
            // or create new card

            System.out.println("Are you sure you want to take your loan on this card? [Y/N]");
            wantsLoahn = INPUT.scanner.next().toLowerCase();
            if(wantsLoahn.equals("y")){
                //todo loahnservice.sumbit(loan)

                System.out.println(MESSAGE.getSuccessfulMessage("taking your loan on this card "));

                System.out.println(" your loan is now on your chosen card and you are allowed to repay it after your graduate");

            }else {
                System.out.println(" you canselled your loan process!");

            }

        } else {
            System.out.println("you didnt want to take study loan");

        }


    }
}
