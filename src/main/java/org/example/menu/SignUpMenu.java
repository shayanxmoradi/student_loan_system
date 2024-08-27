package org.example.menu;

import org.example.entities.Student;
import org.example.entities.University;
import org.example.entities.enums.DegreeType;
import org.example.entities.enums.UniAcceptenceType;
import org.example.entities.enums.UniversityType;
import org.example.menu.util.Input;
import org.example.menu.util.Message;
import org.example.services.baseentity.StudentService;
import org.example.util.PasswordGenerator;

import java.util.Date;
import java.util.InputMismatchException;

public class SignUpMenu {
    private final Input INPUT;
    private final Message MESSAGE;
    private final StudentService BASEENTITYSERVICE;

    public SignUpMenu(Input input, Message message, StudentService baseentityservice) {
        INPUT = input;
        MESSAGE = message;
        BASEENTITYSERVICE = baseentityservice;
    }

    public void show() {
        System.out.println("Welcome to the Sign Up Menu");
        Student student = new Student();

        System.out.println(MESSAGE.getInputMessage("your first name"));
        student.setFirstName(INPUT.scanner.next());
        System.out.println(MESSAGE.getInputMessage("your surname"));
        student.setLastName(INPUT.scanner.next());

        System.out.println(MESSAGE.getInputMessage("your fathername"));
        student.setFatherName(INPUT.scanner.next());
        System.out.println(MESSAGE.getInputMessage("your mothername"));
        student.setMotherName(INPUT.scanner.next());
        System.out.println(MESSAGE.getInputMessage("your national code"));
        student.setNationalCode(INPUT.scanner.next());
        System.out.println(MESSAGE.getInputMessage("your passport number"));
        student.setPassportNumber(INPUT.scanner.next());


        Integer yearOfBirth, monthOfBirth, dayOfBirth;

        try {
            System.out.println(MESSAGE.getInputMessage("year of birth"));
            yearOfBirth = INPUT.scanner.nextInt();

            System.out.println(MESSAGE.getInputMessage("month of birth"));
            monthOfBirth = INPUT.scanner.nextInt();

            System.out.println(MESSAGE.getInputMessage("day of birth"));
            dayOfBirth = INPUT.scanner.nextInt();

            // Validate the date
            if (isValidDate(yearOfBirth, monthOfBirth, dayOfBirth)) {
                // Create a java.sql.Date object
                java.util.Calendar calendar = java.util.Calendar.getInstance();
                calendar.set(yearOfBirth, monthOfBirth - 1, dayOfBirth); // Month is 0-based
                java.sql.Date sqlDateOfBirth = new java.sql.Date(calendar.getTimeInMillis());

                student.setDateOfBirth(sqlDateOfBirth);
            } else {
                System.out.println("Invalid date. Please check the values entered.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter numeric values.");
        }


        System.out.println(MESSAGE.getInputMessage("your student number"));
        student.setStudentNumber(Integer.valueOf(INPUT.scanner.next()));
        //todo check here
        System.out.println(MESSAGE.getInputMessage("your university name"));
        student.setUniversityName(INPUT.scanner.next());

        System.out.println(MESSAGE.getInputMessage("chose your university type"));

        String uniTypeText = "pls chose number your university type \n";
        for (int i = 0; i < UniversityType.values().length; i++) {
            uniTypeText += i + " -> " + UniversityType.values()[i] + "\n";
        }
        System.out.println(uniTypeText);

        int choice = INPUT.scanner.nextInt();
        while (!(choice < UniversityType.values().length)) {
            System.out.println("chose between avialable numbers");
            choice = INPUT.scanner.nextInt();
        }
        student.setUniversityType(UniversityType.values()[choice]);

        System.out.println(MESSAGE.getInputMessage("your enrollment year"));
        student.setEntrollmentYear(INPUT.scanner.nextInt());


        System.out.println(MESSAGE.getInputMessage("chose your degree type"));
        student.setDegreeType(getDegreeType());

        System.out.println(MESSAGE.getInputMessage("chose your university acceptance type"));
        student.setUniAcceptenceType(getUniAcceptenceType());


        student.setPassword(PasswordGenerator.passwordGenerator());


        BASEENTITYSERVICE.save(student);

        MESSAGE.getSuccessfulMessage("your account");
        System.out.println("your Password is: " + student.getPassword() + " (make sure keep it safe)");


    }

    private boolean isValidDate(int year, int month, int day) {
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

    private DegreeType getDegreeType() {
        String degreeTypeText = "Please choose a degree type:\n";
        for (int i = 0; i < DegreeType.values().length; i++) {
            degreeTypeText += i + " -> " + DegreeType.values()[i] + "\n";
        }
        System.out.println(degreeTypeText);

        int choice = INPUT.scanner.nextInt();
        while (choice < 0 || choice >= DegreeType.values().length) {
            System.out.println("Invalid choice. Please choose a valid number.");
            choice = INPUT.scanner.nextInt();
        }
        return DegreeType.values()[choice];
    }

    private UniAcceptenceType getUniAcceptenceType() {
        String uniAcceptenceTypeText = "Please choose a university acceptance type:\n";
        for (int i = 0; i < UniAcceptenceType.values().length; i++) {
            uniAcceptenceTypeText += i + " -> " + UniAcceptenceType.values()[i] + "\n";
        }
        System.out.println(uniAcceptenceTypeText);

        int choice = INPUT.scanner.nextInt();
        while (choice < 0 || choice >= UniAcceptenceType.values().length) {
            System.out.println("Invalid choice. Please choose a valid number.");
            choice = INPUT.scanner.nextInt();
        }
        return UniAcceptenceType.values()[choice];
    }

}