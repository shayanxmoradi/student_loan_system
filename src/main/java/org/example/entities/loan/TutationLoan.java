package org.example.entities.loan;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entities.enums.DegreeType;

@Table(name = "tutation_loan")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TutationLoan extends Loan{

    public static final String TABLE_NAME = "tutation_loan";

    @Enumerated(EnumType.STRING)
    private DegreeType degreeType;

}
