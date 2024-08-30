package org.example.util;

import org.example.entities.enums.DegreeType;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class Utilties {


    @Deprecated
    public static boolean isGraduated(Date enrollmentDate, DegreeType degreeType) {
        LocalDate enrollmentLocalDate = enrollmentDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        LocalDate expectedGraduationDate = enrollmentLocalDate.plusYears(degreeType.getDurationYears());

        return LocalDate.now().isAfter(expectedGraduationDate);
    }

    public static boolean isGraduated(int enrollmentYear, DegreeType degreeType) {


        int duration = enrollmentYear + degreeType.getDurationYears();
        // Compare current date with the expected graduation date
        return LocalDate.now().getYear() >= duration;
    }
//    public static Date graudateDate(int enrollmentYear, DegreeType degreeType) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.YEAR, enrollmentYear + degreeType.getDurationYears());
//        calendar.set(Calendar.MONTH, Calendar.JANUARY);
//        calendar.set(Calendar.DAY_OF_MONTH, 1);
//        calendar.set(Calendar.HOUR_OF_DAY, 0);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
//        calendar.set(Calendar.MILLISECOND, 0);
//        return calendar.getTime();
//    }

    public static java.sql.Date graudateDate(int enrollmentYear, DegreeType degreeType) {
        int newYear = enrollmentYear + degreeType.getDurationYears();

        Calendar calendar = Calendar.getInstance();
        calendar.set(newYear, Calendar.JANUARY, 1); // Month is 0-based (January is 0)

        // Return the SQL Date
        return new java.sql.Date(calendar.getTimeInMillis());
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
