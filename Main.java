package prog5121poe;

import java.util.Scanner;

/**
 * ================================================
 * PROG5121 - Part 1
 * Author    : Bonginkosi Dlamini
 * Student No: ST10511967
 * Date      : April 2026
 * Purpose   : This is the main class that runs the
 *             program. It collects user input using
 *             Scanner and uses the Login class to
 *             register and log in the user.
 * ================================================
 */
public class Main {

    public static void main(String[] args) {

        // Create a Scanner object to read input typed by the user
        Scanner scanner = new Scanner(System.in);

        // ── Welcome Banner ────────────────────────
        // Print a welcome message when the program starts
        System.out.println("======================================");
        System.out.println("   Welcome to the Registration System");
        System.out.println("======================================\n");

        // ── Collect User Details ──────────────────
       
        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine().trim();

        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine().trim();

        System.out.print("Enter a username (must contain _ and be max 5 characters): ");
        String username = scanner.nextLine().trim();

        System.out.print("Enter a password: ");
        String password = scanner.nextLine().trim(); 

        System.out.print("Enter your South African cell number (e.g. +27821234567): ");
        String cellPhone = scanner.nextLine().trim(); 

        // ── Create Login Object ───────────────────
        Login login = new Login(firstName, lastName, username, password, cellPhone);

        // ── Attempt Registration ──────────────────
        System.out.println("\n--- Registration Feedback ---");

        // Call registerUser() which validates all fields and returns a message
        String registrationResult = login.registerUser();

        // Print whatever message registerUser() returned
        System.out.println(registrationResult);

        // ── Check Registration Result ─────────────
        boolean registrationSuccess = registrationResult.contains("successfully captured");

        // ── Login Section ─────────────────────────
        if (registrationSuccess) {

            // Print the login banner
            System.out.println("\n======================================");
            System.out.println("               LOGIN");
            System.out.println("======================================");

            // Ask the user to enter their login credentials
            System.out.print("Enter your username: ");
            String enteredUsername = scanner.nextLine().trim();

            System.out.print("Enter your password: ");
            String enteredPassword = scanner.nextLine().trim(); 

            // Call returnLoginStatus() with the entered credentials
            String loginMessage = login.returnLoginStatus(enteredUsername, enteredPassword);

            // Print the login result message
            System.out.println("\n" + loginMessage);

        } else {
            // Registration failed - tell the user to restart and try again
            System.out.println("\nRegistration failed. Please restart and try again.");
        }

        // ── Close Scanner ─────────────────────────
        scanner.close();
    }
}
