package org.example.menu;

import org.example.entities.Student;
import org.example.entities.enums.UniAcceptenceType;
import org.example.entities.enums.UniversityType;
import org.example.menu.loggedin.LoggedInMenu;
import org.example.menu.util.Input;
import org.example.menu.util.Message;
import org.example.services.student.StudentService;
import org.example.util.AuthHolder;
import org.example.util.Utilties;

public class LoginMenu {
    private final Input INPUT;
    private final Message MESSAGE;
    private final StudentService STUDENT_SERVICE;
    private final AuthHolder AUTH_HOLDER;
    private final LoggedInMenu LOGGED_IN_MENU;

    public LoginMenu(Input input, Message message, StudentService studentService, AuthHolder authHolder, LoggedInMenu loggedInMenu) {
        INPUT = input;
        MESSAGE = message;
        STUDENT_SERVICE = studentService;
        AUTH_HOLDER = authHolder;
        LOGGED_IN_MENU = loggedInMenu;
    }

    public void show() {
        System.out.println("Login menu");

        String nationalCode, password;

        System.out.println(MESSAGE.getInputMessage("Your national Code"));
        nationalCode = INPUT.scanner.next();
        System.out.println(MESSAGE.getInputMessage("Your password"));
        password = INPUT.scanner.next();



        Student loginedStudent = STUDENT_SERVICE.login(nationalCode, password);
        if (loginedStudent != null) {
            storeStudentDetails(nationalCode, password, loginedStudent);

            LOGGED_IN_MENU.show();
        } else {
            System.out.println(MESSAGE.getNotFoundMessage("this student "));
            show();
        }



    }

    private void storeStudentDetails(String nationalCode, String password, Student loginedStudent) {
        AUTH_HOLDER.tokenNationalNumber = nationalCode;
        AUTH_HOLDER.tokenPassword = password;
        AUTH_HOLDER.isGraduated= Utilties.isGraduated(loginedStudent.getEntrollmentYear(), loginedStudent.getDegreeType());
        AUTH_HOLDER.graduatedDate=Utilties.graudateDate(loginedStudent.getEntrollmentYear(),loginedStudent.getDegreeType());
        AUTH_HOLDER.livesInStudentResidence= loginedStudent.isLivesInStudentResidence();
        AUTH_HOLDER.partnerNationalCode= loginedStudent.getPartnerNationalCode();
        AUTH_HOLDER.cityType= loginedStudent.getCityType();
        AUTH_HOLDER.degreeType= loginedStudent.getDegreeType();
        AUTH_HOLDER.isDailyAndPublicUniversity= loginedStudent.getUniversityType() == UniversityType.PUBLIC&& loginedStudent.getUniAcceptenceType() == UniAcceptenceType.DAILY;
        AUTH_HOLDER.student=  loginedStudent;
    }
}
