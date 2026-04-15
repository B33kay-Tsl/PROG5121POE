package prog5121poe;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Welcome to the Registration System ===");
        System.out.println();

        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter a username (must contain _ and be max 5 characters): ");
        String username = scanner.nextLine();

        System.out.print("Enter a password: ");
        String password = scanner.nextLine();

        System.out.print("Enter your South African cell number (e.g. +27821234567): ");
        String cellPhone = scanner.nextLine();

        Login login = new Login(firstName, lastName, username, password, cellPhone);

        if (login.checkCellPhoneNumber()) {
            System.out.println("Cell phone number successfully added.");
        } else {
            System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
        }

        String registrationResult = login.registerUser();
        System.out.println(registrationResult);

        if (registrationResult.startsWith("Welcome")) {
            System.out.println();
            System.out.println("=== Login ===");

            System.out.print("Enter your username to log in: ");
            String enteredUsername = scanner.nextLine();

            System.out.print("Enter your password to log in: ");
            String enteredPassword = scanner.nextLine();

            System.out.println(login.returnLoginStatus(enteredUsername, enteredPassword));
        }

        scanner.close();
    }
}