package org.example.menu.loggedin;

import org.example.menu.registerloan.RegisterForLoanPage;
import org.example.menu.util.Input;
import org.example.menu.util.Message;
import org.example.util.AuthHolder;

public class LoggedInMenu {
    private final AuthHolder AUTH_HOLDER;
    private final Message MESSAGE;
    private final Input INPUT;
    private final RegisterForLoanPage REGISTER_FOR_LOAN_PAGE;
    private final LoanRepaymentPage LOAN_REPAYMENT_PAGE;

    public LoggedInMenu(AuthHolder authHolder, Message message, Input input, RegisterForLoanPage registerForLoanPage, LoanRepaymentPage loanRepaymentPage) {
        this.AUTH_HOLDER = authHolder;
        MESSAGE = message;
        INPUT = input;
        REGISTER_FOR_LOAN_PAGE = registerForLoanPage;
        LOAN_REPAYMENT_PAGE = loanRepaymentPage;
    }

    public void show() {
        System.out.println("welcome " + AUTH_HOLDER.tokenNationalNumber);

        loggedInMenu:
        while (true) {
            System.out.println("""
                    Choose your Operation:
                    1 -> Register For Loan page
                    2-> Loan Repayment page
                    3 -> logout
                    """);

            switch (INPUT.scanner.nextInt()) {
                case 1 -> REGISTER_FOR_LOAN_PAGE.show();
                case 2 -> LOAN_REPAYMENT_PAGE.show();
                case 3 -> {
                    AUTH_HOLDER.reset();
                    break loggedInMenu;
                }
                default -> System.out.println(MESSAGE.getInvalidInputMessage());

            }

        }
    }
}
