package org.example.menu.util;

import org.example.entities.Card;
import org.example.entities.enums.BankType;
import org.example.entities.enums.UniAcceptenceType;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Scanner;

public class CardInfoCollector {
   static Scanner scanner = new Scanner(System.in);


    public static Card collectCardInfo(Card giveCard) {

        String cardNumber = "";
        String ccv2 = "";
        LocalDate expirationDate = null;

        // Get Card Number
        while (true) {
            System.out.print("Enter your card number (16 digits): ");
            cardNumber = scanner.nextLine();
            if (isValidCardNumber(cardNumber)) {
                break;
            } else {
                System.out.println("Invalid card number. Please try again.");
            }
        }

        // Get CCV2
        while (true) {
            System.out.print("Enter your CCV2 (3 digits): ");
            ccv2 = scanner.nextLine();
            if (isValidCCV2(ccv2)) {
                break;
            } else {
                System.out.println("Invalid CCV2. Please try again.");
            }
        }

        // Get Expiration Date
        while (true) {
            System.out.print("Enter your expiration date (MM/yyyy): ");
            String dateInput = scanner.nextLine();
            expirationDate = parseDate(dateInput);
            if (expirationDate != null && expirationDate.isAfter(LocalDate.now())) {
                break;
            } else {
                System.out.println("Invalid expiration date. Please enter a future date.");
            }
        }

        // Convert LocalDate to java.sql.Date
        Date sqlExpirationDate = Date.valueOf(expirationDate);

        // Output the collected information

        giveCard.setCardNummber(cardNumber);
        giveCard.setCvv2(ccv2);
        giveCard.setExpiryDate(sqlExpirationDate);
        giveCard.setBankType(getBankType());
        return giveCard;
    }

    private static boolean isValidCardNumber(String cardNumber) {
        return cardNumber.matches("\\d{16}");
    }

    private static boolean isValidCCV2(String ccv2) {
        return ccv2.matches("\\d{3}");
    }

    private static LocalDate parseDate(String dateInput) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
        sdf.setLenient(false);
        try {
            // Parse the date and convert to LocalDate
            java.util.Date parsedDate = sdf.parse(dateInput);
            return new java.sql.Date(parsedDate.getTime()).toLocalDate();
        } catch (ParseException e) {
            return null;
        }
    }

    private static BankType getBankType() {
        String banktype = "Please choose a Bacnk  type:\n";
        for (int i = 0; i < BankType.values().length; i++) {
            banktype += i + " -> " + BankType.values()[i] + "\n";
        }
        System.out.println(banktype);

        int choice = getValidInt();
        while (choice < 0 || choice >= BankType.values().length) {
            System.out.println("Invalid choice. Please choose a valid number.");
            choice = getValidInt();
        }
        return BankType.values()[choice];
    }

    public static int getValidInt() {
        while (true) {
            System.out.print("Please enter a number: ");
            if (scanner.hasNextInt()) {
                return scanner.nextInt(); // return the valid integer
            } else {
                System.out.println("here you are allowed just give numbers nothing else. Please try again.");
                scanner.next(); // discard the invalid input
            }
        }
    }
}