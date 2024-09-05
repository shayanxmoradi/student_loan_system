package org.example.menu.registerloan;

import org.example.entities.Card;
import org.example.entities.Student;
import org.example.entities.loan.StudyLoan;
import org.example.menu.util.Input;
import org.example.menu.util.Message;
import org.example.services.card.CardService;
import org.example.services.loan.studyloan.StudyLoanService;
import org.example.util.AuthHolder;

import java.util.Calendar;

public class StudyLoanPage {
    private final AuthHolder AUTH_HOLDER;
    private final Input INPUT;
    private final Message MESSAGE;
    private final StudyLoanService STUDY_LOAN_SERVICE;
    private final CardService CARD_SERVICE;
    private final CardPage CARD_PAGE;

    public StudyLoanPage(AuthHolder authHolder, Input input, Message message, StudyLoanService studyLoanService, CardService cardService, CardPage cardPage) {
        AUTH_HOLDER = authHolder;
        INPUT = input;
        MESSAGE = message;
        STUDY_LOAN_SERVICE = studyLoanService;
        CARD_SERVICE = cardService;
        CARD_PAGE = cardPage;
    }

    void show() {

        boolean alreadyTookStudyLoan = STUDY_LOAN_SERVICE.canTakeLoanInThisSemester(AUTH_HOLDER.student);
        if (alreadyTookStudyLoan) {
            //    BigDecimal loanAmount = BigDecimal.valueOf(0);
            System.out.println("welcome To studyloan page");
            System.out.println("based on your Degree Type wich is " + AUTH_HOLDER.degreeType +
                               "you are allowed to take " + AUTH_HOLDER.degreeType.allowedLoanAmount() + " per Semester");

            System.out.println("do you will to take your " + AUTH_HOLDER.degreeType.allowedLoanAmount() + " loan ? [Y/N]");
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

                        setupLoan(student, resualtcard);

                        System.out.println(MESSAGE.getSuccessfulMessage("taking your loan on this card "));

                        System.out.println(" your loan is now on your chosen card and you are allowed to repay it after your graduate");
                        System.out.println();

                    } else {
                        System.out.println(" you cancelled your loan process!");

                    }

                } else {
                    System.out.println("No valid Card, loan Process failed!");

                }
            } else System.out.println("you cancelled loan process!");
        } else {
            System.out.println("you are only allowed to take study loan 1 time per Semester and you " +
                               "already took it one time. you can try again in next Semester");
        }


    }

    private void setupLoan(Student student, Card resualtcard) {
        StudyLoan studyLoan = new StudyLoan();
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());


        studyLoan.setLoanDate(date);
        studyLoan.setAmount(AUTH_HOLDER.degreeType.allowedLoanAmount());
        studyLoan.setInterestRate(4);
        studyLoan.setStudent(student);
        studyLoan.setCard(resualtcard);
        studyLoan.setUpStudyLoan();
        studyLoan.setDegreeType(AUTH_HOLDER.degreeType);
//todo watch out
        studyLoan.setUpInstallments(AUTH_HOLDER);

        STUDY_LOAN_SERVICE.save(studyLoan);
    }


}
