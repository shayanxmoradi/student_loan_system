package org.example.util;

import org.example.entities.enums.DegreeType;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

public class Utilties {


    @Deprecated
    public static boolean isGraduated(Date enrollmentDate, DegreeType degreeType) {
        // Convert Date to LocalDate
        LocalDate enrollmentLocalDate = enrollmentDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        // Add the duration of the degree to the enrollment date
        LocalDate expectedGraduationDate = enrollmentLocalDate.plusYears(degreeType.getDurationYears());

        // Compare current date with the expected graduation date
        return LocalDate.now().isAfter(expectedGraduationDate);
    }

    public static boolean isGraduated(int enrollmentYear, DegreeType degreeType) {


        int duration = enrollmentYear + degreeType.getDurationYears();
        // Compare current date with the expected graduation date
        return LocalDate.now().getYear() >= duration;
    }

    public static boolean isItValidDateToRegisterLoan() {
//todo uncomment this

//    return isFirstWeekOfOctoberOrApril();
        return true;
    }


    public static boolean isFirstWeekOfOctoberOrApril() {
        LocalDate today = LocalDate.now();
        Month currentMonth = today.getMonth();
        int currentWeekOfMonth = today.getDayOfMonth() / 7 + 1;

        if ((currentMonth == Month.OCTOBER && currentWeekOfMonth == 1) ||
            (currentMonth == Month.APRIL && currentWeekOfMonth == 1)) {
            return true;
        }

        return false;
    }

    public static int getCurrentYear() {
        LocalDate localDate = LocalDate.now();

        // Assign year
        return localDate.getYear();

    }

    public static int getCurrentSemester() {
        LocalDate localDate = LocalDate.now();


        // Determine semester number based on month
        int month = localDate.getMonthValue(); // January = 1, December = 12
        if (month >= 1 && month <= 6) {
            return 1; // Winter Semester
        } else {
            return 2; // Summer Semester
        }


    }


}
