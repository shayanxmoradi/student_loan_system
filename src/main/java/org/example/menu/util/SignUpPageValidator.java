package org.example.menu.util;

import org.example.entities.Student;
import org.example.entities.enums.CityType;
import org.example.entities.enums.DegreeType;
import org.example.entities.enums.UniAcceptenceType;

public class SignUpPageValidator {
    private Input INPUT;
    private Message MESSAGE;

    public SignUpPageValidator(Input INPUT, Message MESSAGE) {
        this.INPUT = INPUT;
        this.MESSAGE = MESSAGE;
    }

    public String getStringWithGivenSize(int minLength, int maxLength) {
        String givenInput = INPUT.scanner.next();
        if (minLength == maxLength) {
            if (givenInput.length() != minLength) {
                System.out.println("Invalid input.You must enter exactly " + minLength + " characters");
                return getStringWithGivenSize(minLength, maxLength);
            } else return givenInput;

        }
        if (givenInput.length() < minLength) {
            System.out.println("Invalid input. Please enter at least " + minLength + " characters long.");
            return getStringWithGivenSize(minLength, maxLength);
        }
        if (givenInput.length() > maxLength) {
            System.out.println("Invalid input. Please enter at most " + maxLength + " characters long.");
            return getStringWithGivenSize(minLength, maxLength);
        }
        return givenInput;
    }

    public String getVAlidNationalCode() {
        System.out.println(MESSAGE.getInputMessage("your national code"));

        String validLenghtInput = getStringWithGivenSize(11, 11);

        if (containsOnlyDigits(validLenghtInput)) {
            return validLenghtInput;

        } else {
            System.out.println("you are allwoed to just enter numbers");
            return getVAlidNationalCode();

        }

    }

    public boolean containsOnlyDigits(String givenInput) {
        for (char c : givenInput.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public void setUpCityInfos(Student student) {
        System.out.println(MESSAGE.getInputMessage("City you live in"));
        //todo cityies could be stored in database

        String city = INPUT.scanner.next().toLowerCase();
        student.setCity(city);
        switch (city) {
            case "tehran" -> student.setCityType(CityType.CAPITAL);
            case "gilan", "esfehan", "azarbayejan", "fars", "khozestan", "qom", "khorasan razavi", "alborz" ->
                    student.setCityType(CityType.BIG_CITY);
            default -> student.setCityType(CityType.OTHERS);
        }
    }

    public Result getResult() {
        Integer dayOfBirth;
        Integer monthOfBirth;
        Integer yearOfBirth;
        System.out.println(MESSAGE.getInputMessage("year of birth"));
        yearOfBirth = getValidInt();

        System.out.println(MESSAGE.getInputMessage("month of birth"));
        monthOfBirth = getValidInt();

        System.out.println(MESSAGE.getInputMessage("day of birth"));
        dayOfBirth = getValidInt();
        Result birthDateValues = new Result(yearOfBirth, monthOfBirth, dayOfBirth);
        return birthDateValues;
    }

    public record Result(Integer yearOfBirth, Integer monthOfBirth, Integer dayOfBirth) {
    }

    public boolean isValidDate(int year, int month, int day) {
        // Basic validation for month and day
        if (month < 1 || month > 12) return false;
        if (day < 1 || day > 31) return false;

        // Handle months with different number of days
        if (month == 2) {
            // Check for leap year
            boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
            return day <= (isLeapYear ? 29 : 28);
        }

        // Handle months with 30 days
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            return day <= 30;
        }

        return true; // For months with 31 days
    }

    public DegreeType getDegreeType() {
        String degreeTypeText = "Please choose a degree type:\n";
        for (int i = 0; i < DegreeType.values().length; i++) {
            degreeTypeText += i + " -> " + DegreeType.values()[i] + "\n";
        }
        System.out.println(degreeTypeText);

        int choice = getValidInt();
        while (choice < 0 || choice >= DegreeType.values().length) {
            System.out.println("Invalid choice. Please choose a valid number.");
            choice = getValidInt();
        }
        return DegreeType.values()[choice];
    }

    public UniAcceptenceType getUniAcceptenceType() {
        String uniAcceptenceTypeText = "Please choose a university acceptance type:\n";
        for (int i = 0; i < UniAcceptenceType.values().length; i++) {
            uniAcceptenceTypeText += i + " -> " + UniAcceptenceType.values()[i] + "\n";
        }
        System.out.println(uniAcceptenceTypeText);

        int choice = getValidInt();
        while (choice < 0 || choice >= UniAcceptenceType.values().length) {
            System.out.println("Invalid choice. Please choose a valid number.");
            choice = getValidInt();
        }
        return UniAcceptenceType.values()[choice];
    }

    public int getValidInt() {
        while (true) {
            System.out.print("Please enter a number: ");
            if (INPUT.scanner.hasNextInt()) {
                return INPUT.scanner.nextInt(); // return the valid integer
            } else {
                System.out.println("here you are allowed just give numbers nothing else. Please try again.");
                INPUT.scanner.next(); // discard the invalid input
            }
        }
    }

}
