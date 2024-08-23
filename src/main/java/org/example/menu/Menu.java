package org.example.menu;

import org.example.menu.util.Input;
import org.example.menu.util.Message;

public class Menu {
    private  final SignUpMenu SIGNUP_MENU ;
    private final Input INPUT;
    private final Message MESSAGE;
    private final MainMenu MAINMENU;
    private final LoginMenu LOGIN_MENU;


    public Menu(Input input, Message message, MainMenu mainMenu, LoginMenu loginMenu,SignUpMenu signUpMenu) {
        this.INPUT = input;
        this.MESSAGE = message;
        this.MAINMENU = mainMenu;
        this.LOGIN_MENU = loginMenu;
        this.SIGNUP_MENU = signUpMenu;
    }


    public void show() {
        System.out.println("welcome to the our program");
        while (true) {
            MAINMENU.show();
            switch (INPUT.scanner.next()) {
                case "1":
                   SIGNUP_MENU.show();
                    break;
                case "2":
                    LOGIN_MENU.show();
                    break;
                case "3":
                    System.exit(0);
                    break;
                default:
                    System.out.println(MESSAGE.getInvalidInputMessage());
            }
        }
    }
}
