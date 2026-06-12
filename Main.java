package prog5121poe;

import java.util.Scanner;

/**
 * PROG5121 - Core Execution Loop
 * Author: Bonginkosi Dlamini 
 * Student NO:ST10511967
 * Date: April 2026
 */
public class Main {

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            
            // ── PART 1: USER REGISTRATION ───────────────────────────
            System.out.println("======================================");
            System.out.println("   Welcome to the Registration System");
            System.out.println("======================================\n");
            
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
            
            Login login = new Login(firstName, lastName, username, password, cellPhone);
            
            System.out.println("\n--- Registration Feedback ---");
            String registrationResult = login.registerUser();
            System.out.println(registrationResult);
            
            boolean registrationSuccess = registrationResult.contains("successfully captured");
            
            // ── PART 1: LOGIN AUTHENTICATION ───────────────────────
            if (registrationSuccess) {
                
                System.out.println("\n======================================");
                System.out.println("                LOGIN");
                System.out.println("======================================");
                
                System.out.print("Enter your username: ");
                String enteredUsername = scanner.nextLine().trim();
                
                System.out.print("Enter your password: ");
                String enteredPassword = scanner.nextLine().trim();
                
                String loginMessage = login.returnLoginStatus(enteredUsername, enteredPassword);
                System.out.println("\n" + loginMessage);
                
                boolean loginSuccess = loginMessage.contains("Welcome");
                
                // ── PARTS 2 & 3: MAIN MENU APPLICATION LOOP ───────────
                if (loginSuccess) {
                    boolean running = true;
                    
                    while (running) {
                        System.out.println("\n======================================");
                        System.out.println("             MAIN MENU");
                        System.out.println("======================================");
                        System.out.println("1) Send Messages");
                        System.out.println("2) Show Recently Sent Messages");
                        System.out.println("3) Stored Messages");
                        System.out.println("4) Quit");
                        System.out.print("Choose an option: ");
                        
                        String menuChoice = scanner.nextLine().trim();
                        
                        switch (menuChoice) {
                            
                            case "1":
                                // Compose and configure a new message
                                System.out.print("\nEnter recipient cell number (e.g. +27821234567): ");
                                String recipientCell = scanner.nextLine().trim();
                                
                                System.out.print("Enter your message (max 250 characters): ");
                                String messageText = scanner.nextLine().trim();
                                
                                if (messageText.length() > 250) {
                                    System.out.println("Please enter a message of less than 250 characters.");
                                    break;
                                }
                                
                                Message message = new Message(recipientCell, messageText);
                                
                                if (!message.checkRecipientCell()) {
                                    System.out.println("Cell phone number is not correctly formatted " +
                                            "or does not contain an international code.");
                                    break;
                                }
                                
                                System.out.println("\n--- Message Details ---");
                                System.out.println("Message ID   : " + message.getMessageID());
                                System.out.println("Message Hash : " + message.getMessageHash());
                                
                                System.out.println("\nWhat would you like to do?");
                                System.out.println("1) Send Message");
                                System.out.println("2) Disregard Message");
                                System.out.println("3) Store Message");
                                System.out.print("Choose an option: ");
                                
                                String sendChoice = scanner.nextLine().trim();
                                
                                try {
                                    int sendOption = Integer.parseInt(sendChoice);
                                    System.out.println("\n" + message.SentMessage(sendOption));
                                } catch (NumberFormatException e) {
                                    System.out.println("Invalid option entered.");
                                }
                                break;
                                
                            case "2":
                                // Fetch and display volatile state tracking statistics
                                System.out.println("\n--- Recently Sent Messages ---");
                                Message displayHelper = new Message("", "");
                                displayHelper.printMessages();
                                System.out.println("Total messages sent: " + displayHelper.returnTotalMessages());
                                break;
                                
                            case "3":
                                // Delegate control flow to the archive menu sub-system
                                storedMessagesMenu(scanner);
                                break;
                                
                            case "4":
                                System.out.println("\nThank you for using the system. Goodbye!");
                                running = false;
                                break;
                                
                            default:
                                System.out.println("Invalid option. Please choose 1, 2, 3, or 4.");
                                break;
                        }
                    }
                }
                
            } else {
                System.out.println("\nRegistration failed. Please restart and try again.");
            }
        }
    }

    /**
     * Renders and operates the transactional database sub-menu for archived records.
     */
    private static void storedMessagesMenu(Scanner scanner) {
        boolean inStoredMenu = true;

        while (inStoredMenu) {
            System.out.println("\n======================================");
            System.out.println("         STORED MESSAGES");
            System.out.println("======================================");
            System.out.println("1) Search by Message ID");
            System.out.println("2) Search by Recipient");
            System.out.println("3) Delete Message by Hash");
            System.out.println("4) Display All Stored Messages Report");
            System.out.println("5) Back to Main Menu");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {

                case "1":
                    System.out.print("\nEnter Message ID to search: ");
                    String searchID = scanner.nextLine().trim();

                    String foundMessage = Message.searchByMessageID(searchID);
                    System.out.println("\nMessage found: " + foundMessage);

                    System.out.println("Longest stored message: " + Message.getLongestMessage());
                    break;

                case "2":
                    System.out.print("\nEnter recipient cell number to search: ");
                    String searchRecipient = scanner.nextLine().trim();

                    String recipientMessages = Message.searchByRecipient(searchRecipient);
                    System.out.println("\nMessages for this recipient:\n" + recipientMessages);
                    break;

                case "3":
                    System.out.print("\nEnter Message Hash to delete: ");
                    String deleteHash = scanner.nextLine().trim();

                    String deleteResult = Message.deleteByHash(deleteHash);
                    System.out.println("\n" + deleteResult);
                    break;

                case "4":
                    System.out.println("\n" + Message.displayReport());
                    break;

                case "5":
                    inStoredMenu = false;
                    break;

                default:
                    System.out.println("Invalid option. Please choose 1 to 5.");
                    break;
            }
        }
    }
}
