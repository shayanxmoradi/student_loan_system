package org.example;

import org.example.util.ApplicationContext;
import org.example.util.PasswordGenerator;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class EntryPoint {
    public static void main(String[] args) {
        ApplicationContext.getInstance().getMenu().show();


        System.out.println(PasswordGenerator.passwordGenerator());

//        int a='a';
//int z='z';
//int A='A';
//int Z='Z';
//int one = '1';
//int nine = '9';
//        System.out.println((a +"smal a"+"\n"
//        + z +"smal z"+"\n"+A+"big a"+"\n"+
//                           Z+"big z"+"\n"+one+"one"+"\n"+nine));
//
//    }
}}