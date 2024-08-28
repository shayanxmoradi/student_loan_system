package org.example.entities.loan;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entities.BaseEntity;
import org.example.entities.Student;

import java.math.BigDecimal;
import java.util.Date;

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
    private BigDecimal interestRate;

    @Column(nullable = false)
    private Date loanDate;

    @ManyToOne
    @JoinColumn(name = "student_id",nullable = false)
    private Student student;




}
