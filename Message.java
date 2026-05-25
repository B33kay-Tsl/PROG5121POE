package prog5121poe;

import java.util.ArrayList;
import java.util.Random;

/**
 * ================================================
 * PROG5121 - Part 2
 * Author    : Your Full Name
 * Student No: Your Student Number
 * Date      : April 2026
 * Purpose   : This class handles all messaging
 *             functionality including creating,
 *             sending, storing and displaying
 *             messages.
 * ================================================
 */
public class Message {

    // Fields
    // Stores the unique message ID (auto-generated)
    private String messageID;

    // Stores the recipient's cell number
    private String recipientCell;

    // Stores the message text typed by the user
    private String messageText;

    // Stores the auto-generated hash for this message
    private String messageHash;

    // Tracks how many messages have been sent in total
    private static int totalMessagesSent = 0;

    // Tracks total number of messages created
    private static int messageCounter = 0;

    // Stores all sent messages in a list
    private static ArrayList<String> sentMessages = new ArrayList<>();

    // Stores all stored messages in a list
    private static ArrayList<String> storedMessages = new ArrayList<>();

    // Stores all message hashes
    private static ArrayList<String> messageHashes = new ArrayList<>();

    // Stores all message IDs
    private static ArrayList<String> messageIDs = new ArrayList<>();

    // Stores all recipients
    private static ArrayList<String> recipients = new ArrayList<>();

    // Constructor
    /**
     * Creates a new Message object.
     * Auto-generates the message ID and hash.
     *
     * @param recipientCell The recipient's cell number
     * @param messageText   The message to be sent
     */
    public Message(String recipientCell, String messageText) {
        this.recipientCell = recipientCell; // Save recipient number
        this.messageText   = messageText;   // Save message text
        messageCounter++;                   // Increment message count

        // Auto-generate a unique message ID
        this.messageID   = generateMessageID();

        // Auto-generate the message hash
        this.messageHash = createMessageHash();
    }

    // Auto-generate Message ID
    /**
     * Generates a random 10-character message ID
     * using numbers only.
     *
     * @return A String of 10 random digits
     */
    private String generateMessageID() {
        // Create a Random object to generate random numbers
        Random random = new Random();

        // Build a 10-digit ID using a StringBuilder
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            // Add a random digit (0-9) each time
            id.append(random.nextInt(10));
        }
        return id.toString(); // Return the completed ID
    }

    
    // VALIDATION METHODS
    

    /**
     * Checks if the message ID is valid.
     * Rule: Must not exceed 10 characters.
     *
     * @return true if ID is 10 characters or less
     */
    public boolean checkMessageID() {
        // Check that the message ID length does not exceed 10
        return messageID.length() <= 10;
    }

    /**
     * Checks if the recipient cell number is valid.
     * Rule: Must start with '+' and contain international code.
     *
     * @return true if cell number is correctly formatted
     */
    public boolean checkRecipientCell() {
        // Use regex to validate: starts with +, then digits, correct length
        return recipientCell.matches("^\\+\\d{1,3}\\d{7,10}$");
    }

    // HASH METHOD
  

    /**
     * Creates a unique message hash using:
     * - First two digits of the message ID
     * - Last two digits of the message ID
     * - First word of the message
     * - Last word of the message
     * The hash is converted to uppercase.
     *
     * @return The generated hash as a String
     */
    public String createMessageHash() {
        // Get the first two characters of the message ID
        String firstTwo = messageID.substring(0, 2);

        // Get the last two characters of the message ID
        String lastTwo = messageID.substring(messageID.length() - 2);

        // Split the message into individual words
        String[] words = messageText.trim().split(" ");

        // Get the first word of the message
        String firstWord = words[0];

        // Get the last word of the message
        // If there is only one word, first and last will be the same
        String lastWord = words[words.length - 1];

        // Build the hash by combining all parts
        String hash = firstTwo + ":" + lastTwo + ":" + firstWord + lastWord;

        // Convert entire hash to uppercase and return it
        return hash.toUpperCase();
    }

    // SEND MESSAGE METHOD
    

    /**
     * Handles what happens to the message based on user choice.
     * Options:
     * 1 - Send Message
     * 2 - Disregard Message
     * 3 - Store Message
     *
     * @param choice The user's menu selection (1, 2, or 3)
     * @return A String message confirming the action taken
     */
    public String SentMessage(int choice) {
        // Check which option the user selected
        switch (choice) {

            case 1:
                // User chose to SEND the message
                totalMessagesSent++;        
                sentMessages.add(messageText);  
                messageHashes.add(messageHash); 
                messageIDs.add(messageID);      
                recipients.add(recipientCell); 
                return "Message successfully sent.";

            case 2:
                // User chose to DISREGARD the message
                // Message is deleted and not saved anywhere
                return "Message successfully disregarded.";

            case 3:
                // User chose to STORE the message for later
                storedMessages.add(messageText);
                messageHashes.add(messageHash);  
                messageIDs.add(messageID);       
                recipients.add(recipientCell);   
                return "Message successfully stored.";

            default:
                // User entered an invalid option
                return "Invalid option. Please choose 1, 2, or 3.";
        }
    }

    
    // DISPLAY METHODS
   

    /**
     * Prints all sent messages to the screen.
     * Displays: Message ID, Hash, Recipient, and Message text
     * for every message that was sent.
     */
    public void printMessages() {
        // Check if any messages have been sent
        if (sentMessages.isEmpty()) {
            System.out.println("No messages have been sent yet.");
            return; 
        }

        // Print a header
        System.out.println("\n===== SENT MESSAGES =====");

        // Loop through all sent messages and print each one
        for (int i = 0; i < sentMessages.size(); i++) {
            System.out.println("\nMessage " + (i + 1) + ":");
            System.out.println("Message ID   : " + messageIDs.get(i));
            System.out.println("Message Hash : " + messageHashes.get(i));
            System.out.println("Recipient    : " + recipients.get(i));
            System.out.println("Message      : " + sentMessages.get(i));
            System.out.println("-------------------------");
        }
    }

    /**
     * Returns the total number of messages that have been sent.
     *
     * @return int - total number of sent messages
     */
    public int returnTotalMessages() {
        // Return the static counter that tracks sent messages
        return totalMessagesSent;
    }

    
    // STORE MESSAGE METHOD (own method)
   
    /**
     * Custom method to store the message in the stored list.
     * This is the student's own defined storeMessage() method.
     */
    public void storeMessage() {
        // Add the message to the stored messages list
        storedMessages.add(messageText);
        System.out.println("Message stored successfully.");
    }

    
    // GETTERS

    // Returns the message ID
    public String getMessageID()   { return messageID; }

    // Returns the message hash
    public String getMessageHash() { return messageHash; }

    // Returns the recipient cell number
    public String getRecipientCell() { return recipientCell; }

    // Returns the message text
    public String getMessageText() { return messageText; }

    // Returns the full list of sent messages
    public static ArrayList<String> getSentMessages() { return sentMessages; }

    // Returns the full list of stored messages
    public static ArrayList<String> getStoredMessages() { return storedMessages; }
}