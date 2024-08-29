package org.example.entities.loan;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entities.Student;
import org.example.entities.enums.CityType;

@Table(name = "housing_loan")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HousingLoan extends Loan {
    public static final String TABLE_NAME = "housing_loan";

    @Enumerated(EnumType.STRING)
    private CityType cityType;

    @OneToOne
    Student partnerStudent;//todo partner is not student

}
