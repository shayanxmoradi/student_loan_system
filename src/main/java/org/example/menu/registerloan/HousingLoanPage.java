package org.example.menu.registerloan;

import org.example.menu.util.Input;
import org.example.menu.util.Message;
import org.example.util.AuthHolder;

public class HousingLoanPage {
    private final AuthHolder AUTH_HOLDER;
    private final Message MESSAGE;
    private final Input INPUT;

    public HousingLoanPage(AuthHolder authHolder, Message message, Input input) {
        AUTH_HOLDER = authHolder;
        MESSAGE = message;
        INPUT = input;
    }

    void show() {
        if (AUTH_HOLDER.partnerNationalCode != null) {
            if (AUTH_HOLDER.livesInStudentResidence == false) {

                boolean partnerTookHousingLoan = false;//todo
                if (partnerTookHousingLoan == false) {
                    System.out.println("welcome To housingloan page");

                    //todo

                } else {
                    System.out.println("your partner already took housing loan, so not allowed to take new one");
                }

            } else {

                System.out.println("you are living in student residence, so not allowed for housing loan ");
            }


        } else {
            System.out.println("you are not Allowed for Housing loan cause you are not married!");
        }
    }
}
