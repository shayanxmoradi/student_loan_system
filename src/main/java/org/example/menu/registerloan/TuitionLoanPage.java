package org.example.menu.registerloan;

import org.example.util.AuthHolder;

public class TuitionLoanPage {
    private final AuthHolder AUTH_HOLDER;

    public TuitionLoanPage(AuthHolder authHolder) {
        AUTH_HOLDER = authHolder;
    }

    void show(){
        System.out.println("welcome To tuitionloan page");

        if (AUTH_HOLDER.isDailyAndPublicUniversity()){
            //todo

        }else {
            System.out.println("you are studing in daily public university  and you dont pay any tuitionfee " +
                               "so you are not allowed to take this loan");
        }

    }
}
