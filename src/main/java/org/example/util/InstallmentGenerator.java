package org.example.util;

import org.example.entities.loan.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class InstallmentGenerator {

    public static List<LoanInstallment> generateLoanInstallmentList(Loan loan,AuthHolder authHolder) {

        List<LoanInstallment> loanInstallmentList = new ArrayList<>();
        BigDecimal fullLoanAmount = getFullLoanAmount(loan);
        BigDecimal fullPriceAfterInterest = getFullPriceAfterInterest(fullLoanAmount, 1.04, 5);
        BigDecimal installmentAmount = fullPriceAfterInterest.divide(new BigDecimal(60), 2, BigDecimal.ROUND_HALF_UP);
        Date graudateDate = authHolder.getGraduatedDate();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(graudateDate);


        for (int i = 0; i < 60; i++) {
            LoanInstallment loanInstallment = new LoanInstallment();
            loanInstallment.setLoan(loan);
            loanInstallment.setAmount(installmentAmount);
            loanInstallment.setIsPaiedOff(false);

            if (i > 0) {
                calendar.add(Calendar.MONTH, 1);
            }
            Date updatedDate = new Date(calendar.getTimeInMillis());
            loanInstallment.setInstallmentDate(updatedDate);
            loanInstallmentList.add(loanInstallment);

        }
        return loanInstallmentList;

    }

    public static BigDecimal getFullLoanAmount(Loan loan) {

        BigDecimal installmentAmount;

        if (loan instanceof StudyLoan) {
            installmentAmount = ((StudyLoan) loan).getDegreeType().allowedLoanAmount();
        } else if (loan instanceof TuitionLaon) {
            installmentAmount = ((TuitionLaon) loan).getDegreeType().allowedTuitionLoanAmount();

        } else {
            installmentAmount = ((HousingLoan) loan).getCityType().allowedHousingLoanAmount();
        }
        return installmentAmount;
    }

    public static BigDecimal getFullPriceAfterInterest(BigDecimal loanAmount, double intrestRate, int years) {
        System.out.println("your Token loan Amount is : "+loanAmount);
        System.out.println("Total interest after 5 years would be :"+BigDecimal.valueOf(intrestRate).pow(years));
        System.out.println("in Total you should Pay :"+loanAmount.multiply(BigDecimal.valueOf(intrestRate).pow(years)));

        return loanAmount.multiply(BigDecimal.valueOf(intrestRate).pow(years));


    }
}
