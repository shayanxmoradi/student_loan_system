package org.example.menu.loggedin;

import org.example.menu.util.Input;
import org.example.menu.util.Message;
import org.example.services.loan.LoanService;
import org.example.util.AuthHolder;

public class LoanRepaymentPage {
    private final AuthHolder AUTHHOLDER;
    private final Input INPUT;
    private final Message MESSAGE;
    private final PayLoanPage PAYLOAN_PAGE;
    private final LoanService LOAN_SERVICE;

    public LoanRepaymentPage(AuthHolder authholder, Input input, Message message, PayLoanPage payloanPage, LoanService loanService) {
        AUTHHOLDER = authholder;
        INPUT = input;
        MESSAGE = message;
        PAYLOAN_PAGE = payloanPage;
        LOAN_SERVICE = loanService;
    }

    void  show(){
       //available only after graduate
        boolean isGraduted = !AUTHHOLDER.isGraduated();
        isGraduted=false;//todo faking
        if (isGraduted){
           System.out.println("Loan Repayment is not available for you till you graduate");
       }else {

         repaymentwhile:  while(true){
             System.out.println("""
                     Chose between followin options:
                     1 -> Show already paied Loans
                     2 -> Show unpaied Loans
                     3 -> Pay Loans
                     4 -> Pervious Menu
                     """);
             switch (INPUT.scanner.next()){
                 case "1" ->paidLoans();
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
        PAYLOAN_PAGE.show();

    }

    private void unpaidLoans() {
        System.out.println(LOAN_SERVICE.getUnpaiedLoans(AUTHHOLDER.student));
    }

    private void paidLoans() {
        System.out.println(LOAN_SERVICE.getPaidLoans(AUTHHOLDER.student));

    }
}
