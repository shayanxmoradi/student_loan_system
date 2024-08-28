package org.example.entities.enums;

import java.math.BigDecimal;

public enum DegreeType {
    CONTINUOUS_BACHELOR,
    NON_CONTINUOUS_BACHELOR,
    NON_COUNTINUOUS_MASTER,
    CONTINUOUS_MASTER,
    CONTINUOUS_DOCTORATE,
    NON_CONTINUOUS_DOCTORATE,
    SPECIALIST_DOCTORATE;

    public int getDurationYears() {
        switch (this) {
            case CONTINUOUS_BACHELOR:
                return 4;
            case NON_CONTINUOUS_BACHELOR, NON_COUNTINUOUS_MASTER:
                return 2;
            case CONTINUOUS_MASTER:
                return 6;
            case CONTINUOUS_DOCTORATE,
                 NON_CONTINUOUS_DOCTORATE,
                 SPECIALIST_DOCTORATE:
                return 5;
            default:
                return 4;
        }
    }

    public BigDecimal allowedLoanAmount() {
        switch (this) {
            case CONTINUOUS_BACHELOR, NON_CONTINUOUS_BACHELOR:
                return BigDecimal.valueOf(1900);

            case CONTINUOUS_MASTER, NON_COUNTINUOUS_MASTER, CONTINUOUS_DOCTORATE,
                 SPECIALIST_DOCTORATE:
                return BigDecimal.valueOf(2250);
            case NON_CONTINUOUS_DOCTORATE:
                return BigDecimal.valueOf(2600);

            default:
                return BigDecimal.valueOf(1900);
        }

    }
}
