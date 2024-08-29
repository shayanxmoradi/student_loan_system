package org.example.entities.enums;

import java.math.BigDecimal;

public enum CityType {
    CAPITAL,BIG_CITY,OTHERS;

    public BigDecimal allowedHousingLoanAmount() {
        switch (this) {
            case CAPITAL:
                return BigDecimal.valueOf(3200);

            case BIG_CITY:
                return BigDecimal.valueOf(2600);
            case OTHERS:
                return BigDecimal.valueOf(1950);

            default:
                return BigDecimal.valueOf(1950);
        }

    }
}
