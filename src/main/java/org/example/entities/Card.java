package org.example.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entities.enums.BankType;
import org.example.entities.enums.UniAcceptenceType;

import java.sql.Date;

@Table(name = Card.TABLE_NAME)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Card extends BaseEntity<Long> {
    public static final String TABLE_NAME = "card";
    private static final String BANK_TYPE = "bank_type";
    private static final String CARD_NUMBER = "card_number";
    private static final String CVV2 = "cvv2";

    @Column(nullable = false, name = CARD_NUMBER)
    @Size(min = 16, max = 16, message = "The length must be exactly 16 characters.")
    String cardNummber;

    Date expiryDate;

    @Column(nullable = false, name = CVV2)
    @Size(min = 3, max = 3, message = "The length must be exactly 3 characters.")
    String cvv2;

    @ManyToOne
    @JoinColumn(name = "student_id",nullable = true)
    private Student student;


    @Column(nullable = false, name = BANK_TYPE)
    @Enumerated(EnumType.STRING)
    private BankType bankType;

}
