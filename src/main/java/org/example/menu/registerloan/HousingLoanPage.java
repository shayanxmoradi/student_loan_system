package org.example.menu.registerloan;

import org.example.entities.Card;
import org.example.entities.Student;
import org.example.entities.loan.HousingLoan;
import org.example.entities.loan.StudyLoan;
import org.example.menu.util.Input;
import org.example.menu.util.Message;
import org.example.services.card.CardService;
import org.example.services.loan.housing.HousingLoanService;
import org.example.util.AuthHolder;

import java.util.Calendar;

public class HousingLoanPage {
    private final AuthHolder AUTH_HOLDER;
    private final Message MESSAGE;
    private final Input INPUT;
    private final HousingLoanService HOUSINGLOAN_SERVICE;
    private final CardService CARD_SERVICE;
    private final CardPage CARD_PAGE;

    public HousingLoanPage(AuthHolder authHolder, Message message, Input input, HousingLoanService housingloanService, CardService cardService, CardPage cardPage) {
        AUTH_HOLDER = authHolder;
        MESSAGE = message;
        INPUT = input;
        HOUSINGLOAN_SERVICE = housingloanService;
        CARD_SERVICE = cardService;
        CARD_PAGE = cardPage;
    }

    void show() {
        if (AUTH_HOLDER.partnerNationalCode != null) {
            if (AUTH_HOLDER.livesInStudentResidence == false) {

                boolean canTakeLoan = HOUSINGLOAN_SERVICE.canTakeLoan(AUTH_HOLDER.student);//todo
                if (canTakeLoan) {
                    System.out.println("welcome To housingloan page");




                    System.out.println("based on your City Type wich is " + AUTH_HOLDER.cityType +
                                       "you are allowed to take " + AUTH_HOLDER.cityType.allowedHousingLoanAmount() + " per Semester");

                    System.out.println("do you will to take your " + AUTH_HOLDER.cityType.allowedHousingLoanAmount() + " loan ? [Y/N]");
                    String wantsLoahn = INPUT.scanner.next().toLowerCase();

                    if (wantsLoahn.equals("y")) {
                        // show owned cards
                        Card resualtcard = CARD_PAGE.show();
                        if (resualtcard != null) {

                            // or create new card
                            System.out.println("your chosed card " + resualtcard);
                            System.out.println("Are you sure you want to take your loan on this card? [Y/N]");
                            wantsLoahn = INPUT.scanner.next().toLowerCase();
                            if (wantsLoahn.equals("y")) {

                                Student student = AUTH_HOLDER.student;


                                //todo is it good idea to store hole student object??

                                HousingLoan housingLoan = new HousingLoan();
                                java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());


                                housingLoan.setLoanDate(date);
                                housingLoan.setAmount(AUTH_HOLDER.cityType.allowedHousingLoanAmount());
                                housingLoan.setInterestRate(4);
                                housingLoan.setStudent(student);
                                housingLoan.setCard(resualtcard);
                                housingLoan.setCityType(AUTH_HOLDER.cityType);
                                housingLoan.setUpInstallments(AUTH_HOLDER);

                                housingLoan.setPartnerStudent(HOUSINGLOAN_SERVICE.partnerStudetnt(AUTH_HOLDER.student));


                                HOUSINGLOAN_SERVICE.save(housingLoan);

                                System.out.println(MESSAGE.getSuccessfulMessage("taking your loan on this card "));

                                System.out.println(" your loan is now on your chosen card and you are allowed to repay it after your graduate");
                                System.out.println();

                            } else {
                                System.out.println(" you canselled your loan process!");

                            }

                        } else {
                            System.out.println("No valid Card, loan Process failed!");

                        }
                    } else System.out.println("you cansed loan process!");




                    //todo

                } else {
                    System.out.println("you or your partner already took housing loan, so not allowed to take new one");
                }

            } else {

                System.out.println("you are living in student residence, so not allowed for housing loan ");
            }


        } else {
            System.out.println("you are not Allowed for Housing loan cause you are not married!");
        }
    }
}
