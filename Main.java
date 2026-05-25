package prog5121poe;

import java.util.Scanner;

/**
 * ================================================
 * PROG5121 - Part 1 and 2
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

       
        
        try ( // Create scanner to read keyboard input
                Scanner scanner = new Scanner(System.in)) {
            
            
            // Print welcome banner
            System.out.println("======================================");
            System.out.println("   Welcome to the Registration System");
            System.out.println("======================================\n");
            // Collect user registration details
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
            // Create Login object with entered details
            Login login = new Login(firstName, lastName, username, password, cellPhone);
           
            System.out.println("\n--- Registration Feedback ---");
            String registrationResult = login.registerUser();
            System.out.println(registrationResult);
            // Check if registration was successful
            boolean registrationSuccess = registrationResult.contains("successfully captured");
            
            
            if (registrationSuccess) {
                System.out.println("\n======================================");
                System.out.println("               LOGIN");
                System.out.println("======================================");
                
                // Ask user to enter login credentials
                System.out.print("Enter your username: ");
                String enteredUsername = scanner.nextLine().trim();
                
                System.out.print("Enter your password: ");
                String enteredPassword = scanner.nextLine().trim();
                
                // Get and print login result
                String loginMessage = login.returnLoginStatus(enteredUsername, enteredPassword);
                System.out.println("\n" + loginMessage);
                
                //  show messaging if login was successful
                boolean loginSuccess = loginMessage.contains("Welcome");
                
                // ══════════════════════════════════════
                // PART 2
                // ══════════════════════════════════════
                
                if (loginSuccess) {
                    
                    // Keep showing the menu until user quits
                    boolean running = true;
                    
                    while (running) {
                        
                        // Print the main messaging menu
                        System.out.println("\n======================================");
                        System.out.println("           SEND MESSAGES");
                        System.out.println("======================================");
                        System.out.println("1) Send Messages");
                        System.out.println("2) Show recently sent messages");
                        System.out.println("3) Quit");
                        System.out.print("Choose an option: ");
                        
                        // Read the user's menu choice
                        String menuChoice = scanner.nextLine().trim();
                        
                        switch (menuChoice) {
                            
                            case "1":
                                // Compose a new message
                                
                                // Ask for recipient cell number
                                System.out.print("\nEnter recipient cell number (e.g. +27821234567): ");
                                String recipientCell = scanner.nextLine().trim();
                                
                                // Ask for message text (max 250 characters)
                                System.out.print("Enter your message (max 250 characters): ");
                                String messageText = scanner.nextLine().trim();
                                
                                // Check message length before creating
                                if (messageText.length() > 250) {
                                    System.out.println("Message exceeds 250 characters. " +
                                            "Please reduce the size.");
                                    break; // Go back to menu
                                }
                                
                                // Create the Message object
                                Message message = new Message(recipientCell, messageText);
                                
                                // Validate recipient cell number
                                if (!message.checkRecipientCell()) {
                                    System.out.println("Cell phone number is not correctly " +
                                            "formatted or does not contain an " +
                                            "international code.");
                                    break; // Go back to menu
                                }
                                
                                // Show the generated message details
                                System.out.println("\n--- Message Details ---");
                                System.out.println("Message ID   : " + message.getMessageID());
                                System.out.println("Message Hash : " + message.getMessageHash());
                                
                                // Show the send options menu
                                System.out.println("\nWhat would you like to do?");
                                System.out.println("1) Send Message");
                                System.out.println("2) Disregard Message");
                                System.out.println("3) Store Message");
                                System.out.print("Choose an option: ");
                                
                                // Read the send choice
                                String sendChoice = scanner.nextLine().trim();
                                
                                // Convert to int and call SentMessage()
                                try {
                                    int sendOption = Integer.parseInt(sendChoice);
                                    String result = message.SentMessage(sendOption);
                                    System.out.println("\n" + result);
                                } catch (NumberFormatException e) {
                                    // User typed something that isn't a number
                                    System.out.println("Invalid option entered.");
                                }
                                break;
                                
                           case "2":
                                    // Coming Soon - will be implemented in Part 3
                                    System.out.println("Coming Soon.");
                                    break;
                                
                            case "3":
                                // Quit the program
                                System.out.println("\nThank you for using the messaging system. Goodbye!");
                                running = false; // Exit the while loop
                                break;
                                
                            default:
                                // User typed an invalid menu option
                                System.out.println("Invalid option. Please choose 1, 2, or 3.");
                                break;
                        }
                    }
                }
            } else {
                // Registration failed
                System.out.println("\nRegistration failed. Please restart and try again.");
            }
            
        }
    }
}
