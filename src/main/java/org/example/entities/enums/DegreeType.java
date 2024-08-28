package org.example.entities.enums;

public enum DegreeType {
    CONTINUOUS_BACHELOR,
    NON_CONTINUOUS_BACHELOR,
    NON_COUNTINUOUS_MASTER,
    CONTINUOUS_MASTER,
    DOCTORATE;

    public int getDurationYears() {
        switch (this) {
            case CONTINUOUS_BACHELOR:
                return 4;
            case NON_CONTINUOUS_BACHELOR,NON_COUNTINUOUS_MASTER:
                return 2;
            case CONTINUOUS_MASTER:
                return 6;
                case DOCTORATE:
                    return 5;
            default:
                return 0;
        }
    }
}
