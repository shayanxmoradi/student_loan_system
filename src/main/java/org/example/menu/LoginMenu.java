package org.example.menu;

import org.example.entities.Student;
import org.example.menu.util.Input;
import org.example.menu.util.Message;
import org.example.services.student.StudentService;
import org.example.util.AuthHolder;

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
        try {

            Student login = STUDENT_SERVICE.login(nationalCode, password);
            if (login != null) {
            AUTH_HOLDER.tokenNationalNumber = nationalCode;
            AUTH_HOLDER.tokenPassword = password;
            LOGGED_IN_MENU.show();}else    {
                System.out.println(MESSAGE.getNotFoundMessage("this student "));
                show();
            }
        } catch (Exception e) {
            System.out.println(MESSAGE.getNotFoundMessage("this student "));
            show();
        }


    }
}
