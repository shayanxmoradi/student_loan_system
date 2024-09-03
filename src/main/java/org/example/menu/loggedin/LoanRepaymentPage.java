package org.example.menu.loggedin;

import org.example.entities.BaseEntity;
import org.example.entities.Card;
import org.example.entities.loan.LoanInstallment;
import org.example.menu.util.CardInfoCollector;
import org.example.menu.util.Input;
import org.example.menu.util.Message;
import org.example.services.baseentity.BaseEntityService;
import org.example.services.card.CardService;
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
    private final CardService CARD_SERVICE;

    public LoanRepaymentPage(AuthHolder authholder, Input input, Message message, PayLoanPage payloanPage, LoanService loanService, InstallmentService installmentService, CardService cardService) {
        AUTHHOLDER = authholder;
        INPUT = input;
        MESSAGE = message;
        PAYLOAN_PAGE = payloanPage;
        LOAN_SERVICE = loanService;
        INSTALLMENT_SERVICE = installmentService;
        CARD_SERVICE = cardService;
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

//            //paid loans
//            paidLoans();
//
//            //unpaid loans
//            unpaidLoans();
//
//            //pay loans
//            payLoans();
        }
    }

    private void payLoans() {
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
        Card card = new Card();
        Card chosenCard = CardInfoCollector.collectCardInfo(card);
        List<Card> usercards = CARD_SERVICE.findUsercards(AUTHHOLDER.student.getId());

        boolean isCardValid = usercards.stream().anyMatch(
                card1 -> card1.getCardNummber().equals(card.getCardNummber())
                         & card1.getCvv2().equals(card.getCvv2())
                         & card1.getBankType() == card.getBankType()
                         & card1.getExpiryDate().toString().equals(card.getExpiryDate().toString()));
        //   boolean isCardValid=usercards.stream().equals(chosenCard);


        if (isCardValid) {
            System.out.println(" you chosed this Card : ");
            System.out.println(chosenCard);//todo remove
            System.out.println(" Are you sure you want to pay this installment with given card [Y/N]");
            if (INPUT.scanner.next().toLowerCase().equals("y")) {
                System.out.println("\n" + "-> loaninstallment sucessfully paied! <-" + "\n");
                return true;
            } else {
                System.out.println(" you cansel paying process");
                return false;
            }
        } else
            System.out.println();
        System.out.println("this card is not in your arlreadygiven cards\n" +
                           "paying loaninstallment processes failed!");

        return false;
    }

    private void unpaidLoans() {
        List<LoanInstallment> unpaiedInstallments = INSTALLMENT_SERVICE.getInstallments(AUTHHOLDER.student, false);
        System.out.println("Here are some of your unpaied loans");
        for (int i = 0; i < unpaiedInstallments.size(); i++) {
            System.out.println(i + ") " + unpaiedInstallments.get(i).toString());

        }
    }

    private void paidLoans() {
        List<LoanInstallment> unpaiedInstallments = INSTALLMENT_SERVICE.getInstallments(AUTHHOLDER.student, true);
        System.out.println("Here are some of your paid loans");

        for (int i = 0; i < unpaiedInstallments.size(); i++) {
            System.out.println(i + ") " + unpaiedInstallments.get(i).toString());
        }
    }
}
