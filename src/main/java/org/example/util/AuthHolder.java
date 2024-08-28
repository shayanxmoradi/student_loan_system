package org.example.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthHolder {
    public String tokenNationalNumber = null;
    public String tokenPassword = null;
    public boolean isGraduated = false;
    public String partnerNationalCode = null;
    public boolean livesInStudentResidence=false;

    public void reset() {
        tokenNationalNumber = null;
        tokenPassword = null;
        isGraduated = false;
        partnerNationalCode = null;
        livesInStudentResidence=false;

    }
}