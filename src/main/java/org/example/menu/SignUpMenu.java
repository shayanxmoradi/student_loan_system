package org.example.menu;

import org.example.entities.Student;

import org.example.entities.enums.UniversityType;
import org.example.menu.util.Input;
import org.example.menu.util.Message;
import org.example.menu.util.SignUpPageValidator;
import org.example.services.baseentity.BaseEntityService;
import org.example.util.PasswordGenerator;

import java.util.InputMismatchException;

public class SignUpMenu {
    private final Input INPUT;
    private final Message MESSAGE;
    private final BaseEntityService BASEENTITYSERVICE;

    public SignUpMenu(Input input, Message message, BaseEntityService baseentityservice) {
        INPUT = input;
        MESSAGE = message;
        BASEENTITYSERVICE = baseentityservice;
    }

    public void show() {
        System.out.println("Welcome to the Sign Up Menu");
        Student student = new Student();
        SignUpPageValidator validator = new SignUpPageValidator(INPUT,MESSAGE);

        System.out.println(MESSAGE.getInputMessage("your first name"));
        String validFirstName=validator.getStringWithGivenSize(4,20);
        student.setFirstName(validFirstName);

        System.out.println(MESSAGE.getInputMessage("your surname"));

        student.setLastName(validator.getStringWithGivenSize(4,20));

        System.out.println(MESSAGE.getInputMessage("your father name"));
        student.setFatherName(validator.getStringWithGivenSize(4,20));
        System.out.println(MESSAGE.getInputMessage("your mother name"));
        student.setMotherName(validator.getStringWithGivenSize(4,20));

        System.out.println(MESSAGE.getInputMessage("your national code"));
        student.setNationalCode(validator.getVAlidNationalCode());


        System.out.println(MESSAGE.getInputMessage("your passport number"));
        student.setPassportNumber(validator.getStringWithGivenSize(9,9));


        try {
            while (true) {
                SignUpPageValidator.Result birthDateValues = validator.getResult();

                if (validator.isValidDate(birthDateValues.yearOfBirth(), birthDateValues.monthOfBirth(), birthDateValues.dayOfBirth())) {
                    java.util.Calendar calendar = java.util.Calendar.getInstance();
                    calendar.set(birthDateValues.yearOfBirth(), birthDateValues.monthOfBirth() - 1, birthDateValues.dayOfBirth()); // Month is 0-based
                    java.sql.Date sqlDateOfBirth = new java.sql.Date(calendar.getTimeInMillis());

                    student.setDateOfBirth(sqlDateOfBirth);
                    break;
                } else {
                    System.out.println("Invalid date. Please check the values entered.");

                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter numeric values.");
        }


        System.out.println(MESSAGE.getInputMessage("your student number"));
        student.setStudentNumber(Integer.valueOf(validator.getValidInt()));
        //todo check here
        System.out.println(MESSAGE.getInputMessage("your university name"));
        student.setUniversityName(validator.getStringWithGivenSize(4,20));

        System.out.println(MESSAGE.getInputMessage("chose your university type"));

        String uniTypeText = "pls chose number your university type \n";
        for (int i = 0; i < UniversityType.values().length; i++) {
            uniTypeText += i + " -> " + UniversityType.values()[i] + "\n";
        }
        System.out.println(uniTypeText);

        int choice = validator.getValidInt();
        while (!(choice < UniversityType.values().length)) {
            System.out.println("chose between avialable numbers");
            choice = validator.getValidInt();
        }
        student.setUniversityType(UniversityType.values()[choice]);

        System.out.println(MESSAGE.getInputMessage("your enrollment year"));
        student.setEntrollmentYear(validator.getValidInt());//todo valid year in past


        System.out.println(MESSAGE.getInputMessage("chose your degree type"));
        student.setDegreeType(validator.getDegreeType());

        System.out.println(MESSAGE.getInputMessage("chose your university acceptance type"));
        student.setUniAcceptenceType(validator.getUniAcceptenceType());


        System.out.println("Are you married? [Y/N]");
        String isMarried = INPUT.scanner.next().toLowerCase();
        if (isMarried.equals("y")) {
            System.out.println(MESSAGE.getInputMessage(" your partner National code "));

            student.setPartnerNationalCode(validator.getVAlidNationalCode());

            student.setMarried(true);
        }else {

            student.setMarried(false);
        }



        System.out.println(MESSAGE.getInputMessage(" do you live in Student Residence? (Y/N)"));
        boolean livesInresidence = INPUT.scanner.next().equalsIgnoreCase("y");
        if (livesInresidence) {
            student.setLivesInStudentResidence(true);
        } else student.setLivesInStudentResidence(false);


        validator.setUpCityInfos(student);
        student.setPassword(PasswordGenerator.passwordGenerator());
        BASEENTITYSERVICE.save(student);

        MESSAGE.getSuccessfulMessage("your account");
        System.out.println("-> Your user is your nationalcode : " + student.getNationalCode() + ", And your Password is: " + student.getPassword() + " ( make sure keep it safe) <-");

    }

}
