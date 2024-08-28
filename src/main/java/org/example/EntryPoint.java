package org.example;

import org.example.entities.enums.DegreeType;
import org.example.util.ApplicationContext;
import org.example.util.PasswordGenerator;
import org.example.util.Utilties;

import javax.swing.text.Utilities;
import java.util.Date;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class EntryPoint {
    public static void main(String[] args) {
        ApplicationContext.getInstance().getMenu().show();

//        System.out.println(Utilties.isGraduated(2021, DegreeType.NON_COUNTINUOUS_MASTER));
    }}