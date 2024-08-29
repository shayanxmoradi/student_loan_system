package org.example.menu.registerloan;

import org.example.entities.Card;
import org.example.entities.Student;
import org.example.entities.enums.DegreeType;
import org.example.entities.loan.StudyLoan;
import org.example.entities.loan.TuitionLaon;
import org.example.menu.util.Input;
import org.example.menu.util.Message;
import org.example.services.loan.tuitionloan.TuitionLoanService;
import org.example.util.AuthHolder;

import java.math.BigDecimal;
import java.util.Calendar;

public class TuitionLoanPage {
    private final AuthHolder AUTH_HOLDER;
    private final TuitionLoanService TUITION_LOAN_SERVICE;
    private final CardPage CARD_PAGE;
    private final Message MESSAGE;
    private final Input INPUT;

    public TuitionLoanPage(AuthHolder authHolder, TuitionLoanService studyLoanService, CardPage cardPage, Message message, Input input) {
        AUTH_HOLDER = authHolder;
        TUITION_LOAN_SERVICE = studyLoanService;
        CARD_PAGE = cardPage;
        MESSAGE = message;
        INPUT = input;
    }

    void show() {
        System.out.println("welcome To tuitionloan page");

        if (!AUTH_HOLDER.isDailyAndPublicUniversity()) {
            //todo
            boolean eligibaleForLoan = TUITION_LOAN_SERVICE.canTakeLoanInThisSemester(AUTH_HOLDER.student);
            if (eligibaleForLoan) {


                System.out.println("welcome To Tuition Loan page");
                DegreeType degreeType = AUTH_HOLDER.degreeType;
                BigDecimal allowedTuitionLoanAmount = degreeType.allowedTuitionLoanAmount();
                System.out.println("based on your Degree Type wich is " + degreeType +
                                   "you are allowed to take " + allowedTuitionLoanAmount + " per Semester");

                System.out.println("do you will to take your " + allowedTuitionLoanAmount + " loan ? [Y/N]");
                String wantsLoahn = INPUT.scanner.next().toLowerCase();

                if (wantsLoahn.equals("y")) {
                    // show owned cards
                    Card resualtcard = CARD_PAGE.show();
                    if (resualtcard != null) {

                        System.out.println("your chosed card " + resualtcard);
                        System.out.println("Are you sure you want to take your loan on this card? [Y/N]");
                        wantsLoahn = INPUT.scanner.next().toLowerCase();
                        if (wantsLoahn.equals("y")) {

                            Student student = AUTH_HOLDER.student;


                            //todo is it good idea to store hole student object??

                            TuitionLaon tuitionLaon = new TuitionLaon();
                            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());


                            tuitionLaon.setLoanDate(date);
                            tuitionLaon.setAmount(allowedTuitionLoanAmount);
                            tuitionLaon.setInterestRate(4);
                            tuitionLaon.setStudent(student);
                            tuitionLaon.setCard(resualtcard);
                            tuitionLaon.setUpTutationLoan();
                            tuitionLaon.setDegreeType(degreeType);


                            TUITION_LOAN_SERVICE.save(tuitionLaon);

                            System.out.println(MESSAGE.getSuccessfulMessage("taking your loan on this card "));
                            System.out.println();

                            System.out.println(" your loan is now on your chosen card and you are allowed to repay it after your graduate");

                        } else {
                            System.out.println(" you canselled your loan process!");

                        }

                    } else {
                        System.out.println("No valid Card, loan Process failed!");

                    }
                } else System.out.println("you cansed loan process!");



            } else {
                System.out.println("you are only allowed to take study loan 1 time per Semester and you " +
                                   "already took it one time. you can try again in next Semester");
            }

        } else {
            System.out.println("you are studing in daily public university  and you dont pay any tuitionfee " +
                               "so you are not allowed to take this loan");
        }

    }
}
