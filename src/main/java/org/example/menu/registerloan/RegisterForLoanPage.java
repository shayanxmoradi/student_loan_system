package org.example.menu.registerloan;

import org.example.entities.loan.StudyLoan;
import org.example.menu.util.Input;
import org.example.menu.util.Message;

public class RegisterForLoanPage {
    private  final Message MESSAGE;
    private final Input INPUT;
    private final StudyLoanPage STUDY_LOAN_PAGE;
    private final HousingLoanPage HOUSING_LOAN_PAGE;
    private final TuitionLoanPage TUITION_LOAN_PAGE;

    public RegisterForLoanPage(Message message, Input input, StudyLoanPage studyLoanPage, HousingLoanPage housingLoanPage, TuitionLoanPage tuitionLoanPage) {
        MESSAGE = message;
        INPUT = input;
        STUDY_LOAN_PAGE = studyLoanPage;
        HOUSING_LOAN_PAGE = housingLoanPage;
        TUITION_LOAN_PAGE = tuitionLoanPage;
    }

    private final

    void show(){

        boolean graduated=false;
        if (!graduated){
            //todo

            boolean validDate = true;//todo
            if (validDate){

            registerpage:    while (true){


                System.out.println(MESSAGE.getInputMessage(""" 
                        Wich loan do you want to take?
                        1 -> Study loan
                        2 -> Tuition Loan
                        3 -> Housing Loan
                        4 -> Previous Menu
                        """));
                switch (INPUT.scanner.nextInt()){
                    case 1-> STUDY_LOAN_PAGE.show();
                    case 2-> TUITION_LOAN_PAGE.show();
                    case 3-> HOUSING_LOAN_PAGE.show();
                    case 4 -> {
                        break registerpage;
                    }
                    default -> System.out.println(MESSAGE.getInvalidInputMessage());
                }}

            }else {
                System.out.println("you are not allowed to register for loan at this time" +
                                   "Only available in begging of Semester");
            }


        }else {
            System.out.println("You are already graduated and not allowed take any new loans.");
        }

    }

}
