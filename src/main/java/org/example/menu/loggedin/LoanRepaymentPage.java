package org.example.menu.loggedin;

import org.example.menu.util.Input;
import org.example.menu.util.Message;
import org.example.util.AuthHolder;

public class LoanRepaymentPage {
    private final AuthHolder AUTHHOLDER;
    private final Input INPUT;
    private final Message MESSAGE;
    private final PayLoanPage PAYLOAN_PAGE;

    public LoanRepaymentPage(AuthHolder authholder, Input input, Message message, PayLoanPage payloanPage) {
        AUTHHOLDER = authholder;
        INPUT = input;
        MESSAGE = message;
        PAYLOAN_PAGE = payloanPage;
    }

    void  show(){
       //available only after graduate
       if (!AUTHHOLDER.isGraduated()){
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
    }

    private void paidLoans() {

    }
}
