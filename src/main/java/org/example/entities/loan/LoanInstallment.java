package org.example.entities.loan;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entities.BaseEntity;

import java.math.BigDecimal;
import java.sql.Date;

@Table(name = LoanInstallment.TABLE_NAME)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class LoanInstallment extends BaseEntity<Long> {

    public static final String TABLE_NAME ="loan_installment" ;

    @ManyToOne
    @JoinColumn(name = "loan_id")
    private Loan loan;


    private Date installmentDate;

    private Date paiedDate;

    private BigDecimal amount;

    private Boolean isPaiedOff;


}