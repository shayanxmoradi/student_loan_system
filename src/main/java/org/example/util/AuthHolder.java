package org.example.util;

import lombok.Getter;
import lombok.Setter;
import org.example.entities.Student;
import org.example.entities.enums.CityType;
import org.example.entities.enums.DegreeType;

@Getter
@Setter
public class AuthHolder {
    public String tokenNationalNumber = null;
    public String tokenStudentId = null;
    public String tokenPassword = null;

    public boolean isGraduated = false;
    public String partnerNationalCode = null;
    public boolean livesInStudentResidence = false;
    public CityType cityType = null;
    public DegreeType degreeType = null;

    public boolean isDailyAndPublicUniversity = false;
    public Student student = null;

    public void reset() {
        tokenNationalNumber = null;
        tokenStudentId = null;
        tokenPassword = null;
        isGraduated = false;
        partnerNationalCode = null;
        livesInStudentResidence = false;
        cityType = null;
        degreeType = null;
        isDailyAndPublicUniversity = false;
        student = null;


    }
}