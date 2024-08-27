package org.example.menu;

import org.example.util.AuthHolder;

public class LoggedInMenu {
    private final AuthHolder AUTH_HOLDER;

    public LoggedInMenu(AuthHolder authHolder) {
        this.AUTH_HOLDER = authHolder;
    }

    public void show() {
    System.out.println("welcome " + AUTH_HOLDER.tokenNationalNumber);
    }
}
