package org.example.util;

import lombok.Getter;
import lombok.Setter;
import org.example.entities.enums.CityType;

@Getter
@Setter
public class AuthHolder {
    public String tokenNationalNumber = null;
    public String tokenPassword = null;

    public boolean isGraduated = false;
    public String partnerNationalCode = null;
    public boolean livesInStudentResidence=false;
    public CityType cityType = null;

    public void reset() {
        tokenNationalNumber = null;
        tokenPassword = null;
        isGraduated = false;
        partnerNationalCode = null;
        livesInStudentResidence=false;
      cityType = null;


    }
}