package org.example.menu.loggedin;

import org.example.entities.BaseEntity;
import org.example.entities.loan.LoanInstallment;
import org.example.menu.util.Input;
import org.example.menu.util.Message;
import org.example.services.baseentity.BaseEntityService;
import org.example.services.loan.LoanService;
import org.example.services.loan.installment.InstallmentService;
import org.example.util.AuthHolder;

import java.util.List;

public class LoanRepaymentPage {
    private final AuthHolder AUTHHOLDER;
    private final Input INPUT;
    private final Message MESSAGE;
    private final PayLoanPage PAYLOAN_PAGE;
    private final LoanService LOAN_SERVICE;
    private final InstallmentService INSTALLMENT_SERVICE;

    public LoanRepaymentPage(AuthHolder authholder, Input input, Message message, PayLoanPage payloanPage, LoanService loanService, InstallmentService installmentService) {
        AUTHHOLDER = authholder;
        INPUT = input;
        MESSAGE = message;
        PAYLOAN_PAGE = payloanPage;
        LOAN_SERVICE = loanService;
        INSTALLMENT_SERVICE = installmentService;
    }

    void show() {
        //available only after graduate
        boolean isGraduted = !AUTHHOLDER.isGraduated();
        isGraduted = false;//todo faking
        if (isGraduted) {
            System.out.println("Loan Repayment is not available for you till you graduate");
        } else {

            repaymentwhile:
            while (true) {
                System.out.println("""
                        Chose between followin options:
                        1 -> Show already paied Loans
                        2 -> Show unpaied Loans
                        3 -> Pay Loans
                        4 -> Pervious Menu
                        """);
                switch (INPUT.scanner.next()) {
                    case "1" -> paidLoans();
                    case "2" -> unpaidLoans();
                    case "3" -> payLoans();

                    case "4" -> {
                        break repaymentwhile;
                    }
                    default -> System.out.println(MESSAGE.getInvalidInputMessage());
                }


            }


            //paid loans
            paidLoans();

            //unpaid loans
            unpaidLoans();

            //pay loans
            payLoans();


        }
    }

    private void payLoans() {
        //    PAYLOAN_PAGE.show();

        System.out.println(MESSAGE.getInputMessage("loan number you want ot pay off:"));
        List<LoanInstallment> unpaiedInstallments = INSTALLMENT_SERVICE.getInstallments(AUTHHOLDER.student, false);

        for (int i = 0; i < unpaiedInstallments.size(); i++) {
            System.out.println(i + ") " + unpaiedInstallments.get(i).toString());

        }
        try {

            int chosedLoan = Integer.parseInt(INPUT.scanner.next());
            LoanInstallment chosedInstallment = unpaiedInstallments.get(chosedLoan);

            chosedInstallment.setIsPaiedOff(payInstallment(chosedInstallment));
            INSTALLMENT_SERVICE.update(chosedInstallment);

        } catch (NumberFormatException e) {
            System.out.println("you enter unvalid number");
            payLoans();
        }

    }

    private Boolean payInstallment(LoanInstallment chosedInstallment) {
        return true;//todo
    }

    private void unpaidLoans() {
        List<LoanInstallment> unpaiedInstallments = INSTALLMENT_SERVICE.getInstallments(AUTHHOLDER.student, false);

        for (int i = 0; i < unpaiedInstallments.size(); i++) {
            System.out.println(i + ") " + unpaiedInstallments.get(i).toString());

        }

    }

    private void paidLoans() {
        List<LoanInstallment> unpaiedInstallments = INSTALLMENT_SERVICE.getInstallments(AUTHHOLDER.student, true);

        for (int i = 0; i < unpaiedInstallments.size(); i++) {
            System.out.println(i + ") " + unpaiedInstallments.get(i).toString());

        }
    }
}
