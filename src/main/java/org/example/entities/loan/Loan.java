package org.example.entities.loan;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entities.BaseEntity;
import org.example.entities.Card;
import org.example.entities.Student;
import org.example.util.AuthHolder;
import org.example.util.InstallmentGenerator;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Table(name = Loan.TABLE_NAME)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Loan extends BaseEntity<Long> {

    public static final String TABLE_NAME = "loan";

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private double interestRate;

    @Column(nullable = false)
    private Date loanDate;

    @Column(nullable = false)
    private boolean isPaiedOff = false;


    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "card_id", nullable = false)
    private Card card;


    @OneToMany(mappedBy = TABLE_NAME, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LoanInstallment> loanInstallments;


//todo watchout
    public void setUpInstallments(AuthHolder authHolder) {
        loanInstallments= InstallmentGenerator.generateLoanInstallmentList(this,authHolder);
        System.out.println(loanInstallments);
    }

}
