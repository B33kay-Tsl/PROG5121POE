package prog5121poe;

import java.util.ArrayList;
import java.util.Random;

/**
 * PROG5121 - Messaging Functionality
 * Author: Bonginkosi Dlamini (ST10511967)
 * Date: April 2026
 */
public class Message {

    // ── Data Fields ───────────────────────────────────────────────
    private String messageID;       // 10-digit random unique ID
    private String recipientCell;   // Validated international cell number
    private String messageText;
    private String messageHash;     // Upper-case compound tracking key

    // ── Metrics & Counters ────────────────────────────────────────
    private static int totalMessagesSent = 0;
    private static int messageCounter    = 0;

    // ── Part 2: Dynamic Storage (ArrayLists) ──────────────────────
    private static ArrayList<String> sentMessages    = new ArrayList<>();
    private static ArrayList<String> storedMessages  = new ArrayList<>();
    private static ArrayList<String> messageHashes   = new ArrayList<>();
    private static ArrayList<String> messageIDs      = new ArrayList<>();
    private static ArrayList<String> recipients      = new ArrayList<>();

    // ── Part 3: Fixed Storage (Arrays) ────────────────────────────
    private static final int MAX_MESSAGES = 100;

    // Category Arrays
    private static String[] sentMessagesArray    = new String[MAX_MESSAGES];
    private static String[] storedMessagesArray  = new String[MAX_MESSAGES];
    private static String[] disregardedArray     = new String[MAX_MESSAGES];

    // Master Tracking Arrays
    private static String[] allMessagesArray     = new String[MAX_MESSAGES];
    private static String[] messageIDArray       = new String[MAX_MESSAGES];
    private static String[] messageHashArray     = new String[MAX_MESSAGES];
    private static String[] recipientArray       = new String[MAX_MESSAGES];
    private static String[] allFlagsArray        = new String[MAX_MESSAGES];

    // Array Allocation Trackers
    private static int sentCount        = 0;
    private static int storedCount      = 0;
    private static int disregardedCount = 0;
    private static int allCount         = 0;

    /**
     * Initializes a new Message and auto-generates its unique tracking properties.
     */
    public Message(String recipientCell, String messageText) {
        this.recipientCell = recipientCell;
        this.messageText   = messageText;
        messageCounter++;
        this.messageID     = generateMessageID();
        this.messageHash   = createMessageHash();
    }

    /**
     * Generates a unique 10-digit numeric ID string.
     */
    private String generateMessageID() {
        Random random = new Random();
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            id.append(random.nextInt(10));
        }
        return id.toString();
    }

    // ── Validation Methods ────────────────────────────────────────

    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    /**
     * Validates international cell formats (e.g., +27821234567).
     */
    public boolean checkRecipientCell() {
        return recipientCell.matches("^\\+\\d{1,3}\\d{7,10}$");
    }

    // ── Utilities & Business Logic ───────────────────────────────

    /**
     * Generates an uppercase signature: [ID First 2]:[ID Last 2]:[First Word][Last Word]
     */
    public String createMessageHash() {
        String firstTwo  = messageID.substring(0, 2);
        String lastTwo   = messageID.substring(messageID.length() - 2);
        String[] words   = messageText.trim().split("\\s+");
        String firstWord = words[0];
        String lastWord  = words[words.length - 1];
        String hash      = firstTwo + ":" + lastTwo + ":" + firstWord + lastWord;
        return hash.toUpperCase();
    }

    /**
     * Routes the message to parallel data structures.
     * @param choice 1 = Send, 2 = Disregard, 3 = Store
     */
    public String SentMessage(int choice) {
        switch (choice) {
            case 1:
                totalMessagesSent++;
                sentMessages.add(messageText);
                messageHashes.add(messageHash);
                messageIDs.add(messageID);
                recipients.add(recipientCell);
                if (sentCount < MAX_MESSAGES) {
                    sentMessagesArray[sentCount] = messageText;
                    sentCount++;
                }
                addToAllArrays("Sent");
                return "Message successfully sent.";

            case 2:
                if (disregardedCount < MAX_MESSAGES) {
                    disregardedArray[disregardedCount] = messageText;
                    disregardedCount++;
                }
                addToAllArrays("Disregarded");
                return "Message successfully disregarded.";

            case 3:
                storedMessages.add(messageText);
                messageHashes.add(messageHash);
                messageIDs.add(messageID);
                recipients.add(recipientCell);
                if (storedCount < MAX_MESSAGES) {
                    storedMessagesArray[storedCount] = messageText;
                    storedCount++;
                }
                addToAllArrays("Stored");
                return "Message successfully stored.";

            default:
                return "Invalid option. Please choose 1, 2, or 3.";
        }
    }

    /**
     * Centralized backup ledger writing to master tracking arrays.
     */
    private void addToAllArrays(String flag) {
        if (allCount < MAX_MESSAGES) {
            allMessagesArray[allCount] = messageText;
            messageIDArray[allCount]   = messageID;
            messageHashArray[allCount] = messageHash;
            recipientArray[allCount]   = recipientCell;
            allFlagsArray[allCount]    = flag;
            allCount++;
        }
    }

    // ── Output & Search Engines ───────────────────────────────────

    public void printMessages() {
        if (sentMessages.isEmpty()) {
            System.out.println("No messages have been sent yet.");
            return;
        }
        System.out.println("\n===== SENT MESSAGES =====");
        for (int i = 0; i < sentMessages.size(); i++) {
            System.out.println("\nMessage " + (i + 1) + ":");
            System.out.println("Message ID   : " + messageIDs.get(i));
            System.out.println("Message Hash : " + messageHashes.get(i));
            System.out.println("Recipient    : " + recipients.get(i));
            System.out.println("Message      : " + sentMessages.get(i));
            System.out.println("-------------------------");
        }
    }

    public int returnTotalMessages() {
        return totalMessagesSent;
    }

    public void storeMessage() {
        storedMessages.add(messageText);
        System.out.println("Message stored successfully.");
    }

    public static String returnSentMessages() {
        if (sentCount == 0) return "No messages sent.";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sentCount; i++) {
            if (i > 0) sb.append("\n");
            sb.append(sentMessagesArray[i]);
        }
        return sb.toString();
    }

    public static String getLongestMessage() {
        if (storedCount == 0) return "No stored messages found.";
        String longest = storedMessagesArray[0];
        for (int i = 1; i < storedCount; i++) {
            if (storedMessagesArray[i].length() > longest.length()) {
                longest = storedMessagesArray[i];
            }
        }
        return longest;
    }

    public static String searchByMessageID(String id) {
        for (int i = 0; i < allCount; i++) {
            if (messageIDArray[i] != null && messageIDArray[i].equals(id)) {
                return allMessagesArray[i];
            }
        }
        return "Message ID not found.";
    }

    public static String searchByRecipient(String recipient) {
        StringBuilder result = new StringBuilder();
        boolean found = false;
        for (int i = 0; i < allCount; i++) {
            if (recipientArray[i] != null && recipientArray[i].equals(recipient)) {
                if (found) result.append("\n");
                result.append(allMessagesArray[i]);
                found = true;
            }
        }
        if (!found) return "No messages found for this recipient.";
        return result.toString();
    }

    /**
     * Locates a targeted entry by hash key, wipes it, and shifts subsequent cells left.
     */
    public static String deleteByHash(String hash) {
        for (int i = 0; i < allCount; i++) {
            if (messageHashArray[i] != null && messageHashArray[i].equals(hash)) {
                String deletedHash = messageHashArray[i];
                
                // Array shift algorithm
                for (int j = i; j < allCount - 1; j++) {
                    allMessagesArray[j]  = allMessagesArray[j + 1];
                    messageIDArray[j]    = messageIDArray[j + 1];
                    messageHashArray[j]  = messageHashArray[j + 1];
                    recipientArray[j]    = recipientArray[j + 1];
                    allFlagsArray[j]     = allFlagsArray[j + 1];
                }
                
                // Clear dangling references at trailing index
                allMessagesArray[allCount - 1]  = null;
                messageIDArray[allCount - 1]    = null;
                messageHashArray[allCount - 1]  = null;
                recipientArray[allCount - 1]    = null;
                allFlagsArray[allCount - 1]     = null;
                allCount--;
                return "Message Hash " + deletedHash + " successfully deleted.";
            }
        }
        return "Message Hash not found.";
    }

    public static String displayReport() {
        if (storedCount == 0) return "No stored messages to display.";
        StringBuilder report = new StringBuilder();
        report.append("===== STORED MESSAGES REPORT =====");
        for (int i = 0; i < allCount; i++) {
            if ("Stored".equals(allFlagsArray[i])) {
                report.append("\n\nMessage ID   : ").append(messageIDArray[i]);
                report.append("\nMessage Hash : ").append(messageHashArray[i]);
                report.append("\nRecipient    : ").append(recipientArray[i]);
                report.append("\nMessage      : ").append(allMessagesArray[i]);
                report.append("\n-------------------------");
            }
        }
        return report.toString();
    }

    /**
     * Flushes states across all storage variants (Primarily for Unit Test fixtures).
     */
    public static void resetAll() {
        sentMessages   = new ArrayList<>();
        storedMessages = new ArrayList<>();
        messageHashes  = new ArrayList<>();
        messageIDs     = new ArrayList<>();
        recipients     = new ArrayList<>();

        sentMessagesArray   = new String[MAX_MESSAGES];
        storedMessagesArray = new String[MAX_MESSAGES];
        disregardedArray    = new String[MAX_MESSAGES];
        allMessagesArray    = new String[MAX_MESSAGES];
        messageIDArray      = new String[MAX_MESSAGES];
        messageHashArray    = new String[MAX_MESSAGES];
        recipientArray      = new String[MAX_MESSAGES];
        allFlagsArray       = new String[MAX_MESSAGES];

        sentCount        = 0;
        storedCount      = 0;
        disregardedCount = 0;
        allCount         = 0;
        totalMessagesSent = 0;
        messageCounter   = 0;
    }

    // ── Getters ───────────────────────────────────────────────────
    public String getMessageID()     { return messageID; }
    public String getMessageHash()   { return messageHash; }
    public String getRecipientCell() { return recipientCell; }
    public String getMessageText()   { return messageText; }

    public static String[] getSentMessagesArray()   { return sentMessagesArray; }
    public static String[] getStoredMessagesArray() { return storedMessagesArray; }
    public static int getSentCount()                { return sentCount; }
    public static int getStoredCount()              { return storedCount; }
    public static ArrayList<String> getSentMessages()   { return sentMessages; }
    public static ArrayList<String> getStoredMessages() { return storedMessages; }
}
