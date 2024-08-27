package org.example.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthHolder {
    public String tokenNationalNumber = null;
    public String tokenPassword = null;

    public void reset() {
        tokenNationalNumber = null;
        tokenPassword = null;
    }
}