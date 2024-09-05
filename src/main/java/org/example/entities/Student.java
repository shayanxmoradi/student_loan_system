package org.example.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entities.enums.CityType;
import org.example.entities.enums.DegreeType;
import org.example.entities.enums.UniAcceptenceType;
import org.example.entities.enums.UniversityType;
import org.example.entities.loan.Loan;
import org.example.entities.validator.universitynamevalidator.ValidUniversityName;
import org.example.util.PasswordGenerator;
import org.hibernate.validator.constraints.Length;

import java.sql.Date;
import java.util.List;

@Table(name = Student.TABLE_NAME)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)

public class Student extends BaseEntity<Long> {
    public static final String TABLE_NAME = "student";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String FATHER_NAME = "father_name";
    private static final String MOTHER_NAME = "mother_name";
    private static final String NATIONAL_CODE = "national_code";
    private static final String PARTNER_NATIONAL_CODE = "partner_national_code";
    private static final String PASSPORT_NUMBER = "passport_number";
    private static final String DATE_OF_BIRTH = "DATE_OF_BIRTH";
    private static final String STUDENT_NUMBER = "STUDENT_NUMBER";
    private static final String UNIVERSITY_TYPE = "UNIVERSITY_TYPE";
    private static final String UNIVERSITY_NAME = "UNIVERSITY_NAME";
    private static final String DEGREE_TYPE = "DEGREE_TYPE";
    private static final String ENROLMENT_YEAR = "ENROLMENT_YEAR";
    private static final String UNI_ACCEPTENCE_TYPE = "UNI_ACCEPTENCE_TYPE";
    private static final String PASSWORD = "PASSWORD";


    @Column(nullable = false, name = FIRST_NAME)
    @Size(min = 4, max = 100, message = "minimum  length is 4 and max is 100")
    private String firstName;

    @Column(nullable = false, name = LAST_NAME)
    @Size(min = 4, max = 100, message = "minimum  length is 4 and max is 100")
    private String lastName;

    @Column(nullable = false, name = FATHER_NAME)
    @Size(min = 4, max = 100, message = "minimum  length is 4 and max is 100")
    private String fatherName;
    @Column(nullable = false, name = MOTHER_NAME)
    @Size(min = 4, max = 100, message = "minimum  length is 4 and max is 100")
    private String motherName;

    @Column(nullable = false, name = NATIONAL_CODE,unique = true)
    @Size(min = 11, max = 11, message = "The length must be exactly 11 characters.")
    private String nationalCode;

    @Column(nullable = false, name = PASSPORT_NUMBER)
    @Size(min = 9, max = 9, message = "The length must be exactly 9 characters.")
    @Pattern(regexp = "^[A-Za-z]\\d+$", message = "Passport code must start with a single letter followed by numbers.")
    private String passportNumber;

    @Column(nullable = false, name = DATE_OF_BIRTH)
    @Past(message = "Date of birth must be in the past.")
    private Date dateOfBirth;
    @Column(nullable = false, name = STUDENT_NUMBER)
    @Size(min = 10, max = 10, message = "The length must be exactly 10 characters.")
    private Integer studentNumber;

    @Column(nullable = false, name = UNIVERSITY_NAME)
    @ValidUniversityName
    private String universityName;




    @Column(name = "UNIVERSITY_TYPE", nullable = false)
    @Enumerated(EnumType.STRING) // Assuming this is an enum
    private UniversityType universityType;

  @Past(message = "Year of Enrollment must be in the past.")
    @Column(nullable = false, name = ENROLMENT_YEAR)
    private int entrollmentYear;

    @Column(nullable = false, name = DEGREE_TYPE)
    @Enumerated(EnumType.STRING)
    private DegreeType degreeType;

    //todo watchout this only requires when its public uni
    @Column(nullable = false, name = UNI_ACCEPTENCE_TYPE)
    @Enumerated(EnumType.STRING)
    private UniAcceptenceType uniAcceptenceType;

    @Column(nullable = false, name = PASSWORD)
    private String password= PasswordGenerator.passwordGenerator();


    @OneToMany(mappedBy =TABLE_NAME)
    @Column(nullable = true)
    private List<Card> cardList;

    @OneToMany(mappedBy = TABLE_NAME)
    private List<Loan> loanList;

    private boolean married;

    @Column(nullable = true, name = PARTNER_NATIONAL_CODE)
    @Size(min = 11, max = 11, message = "The length must be exactly 11 characters.")
    private String partnerNationalCode;

    private boolean livesInStudentResidence;

    private String city;

    @Enumerated(EnumType.STRING)
    private CityType cityType;


}
