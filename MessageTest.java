package prog5121poe;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * ================================================
 * PROG5121 - Part 2
 * Author    : Bonginkosi Dlamini
 * Student No: St10511967
 * Date      : April 2026
 * Purpose   : JUnit tests for the Message class
 * ================================================
 */
public class MessageTest {


    /**
     * Test 1: Message ID is auto-generated and valid
     * Expected: checkMessageID() returns TRUE
     * Reason: Auto-generated ID is always 10 characters
     */
    @Test
    public void testMessageIDNotMoreThanTenCharacters() {
        // Create a message object with test data
        Message message = new Message("+27718693002",
                "Hi Mike, can you join us for dinner tonight?");

        // Check that the message ID does not exceed 10 characters
        assertTrue(message.checkMessageID());
    }

    /**
     * Test 2: Recipient cell number is correctly formatted
     * Expected: checkRecipientCell() returns TRUE
     * Reason: +27718693002 starts with + and has correct length
     */
    @Test
    public void testRecipientCellCorrectlyFormatted() {
        // Create message with valid recipient number
        Message message = new Message("+27718693002",
                "Hi Mike, can you join us for dinner tonight?");

        // Check that the recipient number passes validation
        assertTrue(message.checkRecipientCell());
    }

    /**
     * Test 3: Recipient cell number is incorrectly formatted
     * Expected: checkRecipientCell() returns FALSE
     * Reason: Number does not start with + or international code
     */
    @Test
    public void testRecipientCellIncorrectlyFormatted() {
        // Create message with invalid recipient number (no + or country code)
        Message message = new Message("08966553",
                "Hi Mike, can you join us for dinner tonight?");

        // Check that the recipient number fails validation
        assertFalse(message.checkRecipientCell());
    }

    /**
     * Test 4: Message hash is correctly generated
     * Expected: Hash is not null or empty
     * Reason: createMessageHash() should always produce a value
     */
    @Test
    public void testMessageHashCreated() {
        // Create a message object
        Message message = new Message("+27718693002",
                "Hi Mike, can you join us for dinner tonight?");

        // Get the generated hash
        String hash = message.getMessageHash();

        // Hash should not be null
        assertNotNull(hash);

        // Hash should not be empty
        assertFalse(hash.isEmpty());
    }

    /**
     * Test 5: Message hash contains correct components
     * Expected: Hash is in uppercase
     * Reason: createMessageHash() converts to uppercase
     */
    @Test
    public void testMessageHashIsUpperCase() {
        // Create a message object
        Message message = new Message("+27718693002",
                "Hi Mike, can you join us for dinner tonight?");

        // Get the hash
        String hash = message.getMessageHash();

        // Check hash equals its own uppercase version
        assertEquals(hash, hash.toUpperCase());
    }


    /**
     * Test 6: Message is successfully sent
     * Expected: SentMessage(1) returns "Message successfully sent."
     * Reason: Option 1 means send
     */
    @Test
    public void testMessageSuccessfullySent() {
        // Create a message with valid test data
        Message message = new Message("+27718693002",
                "Hi Mike, can you join us for dinner tonight?");

        // Send the message by choosing option 1
        String result = message.SentMessage(1);

        // Check that the correct success message is returned
        assertEquals("Message successfully sent.", result);
    }

    /**
     * Test 7: Message is successfully disregarded
     * Expected: SentMessage(2) returns "Message successfully disregarded."
     * Reason: Option 2 means disregard
     */
    @Test
    public void testMessageSuccessfullyDisregarded() {
        // Create a message object
        Message message = new Message("+27718693002",
                "Hi Mike, can you join us for dinner tonight?");

        // Disregard the message by choosing option 2
        String result = message.SentMessage(2);

        // Check that the correct disregard message is returned
        assertEquals("Message successfully disregarded.", result);
    }

    /**
     * Test 8: Message is successfully stored
     * Expected: SentMessage(3) returns "Message successfully stored."
     * Reason: Option 3 means store
     */
    @Test
    public void testMessageSuccessfullyStored() {
        // Create a message object
        Message message = new Message("+27718693002",
                "Hi Mike, can you join us for dinner tonight?");

        // Store the message by choosing option 3
        String result = message.SentMessage(3);

        // Check that the correct store message is returned
        assertEquals("Message successfully stored.", result);
    }

    /**
     * Test 9: Total messages sent is tracked correctly
     * Expected: returnTotalMessages() returns correct count
     * Reason: Counter increments each time a message is sent
     */
    @Test
    public void testReturnTotalMessagesSent() {
        // Create two messages and send both
        Message message1 = new Message("+27718693002",
                "Hi Mike, can you join us for dinner tonight?");
        message1.SentMessage(1); // Send message 1

        Message message2 = new Message("+27838484567",
                "Hi Keegan, did you receive the payment?");
        message2.SentMessage(1); // Send message 2

        // Total sent should now be at least 2
        // (may be higher if other tests ran first)
        assertTrue(message2.returnTotalMessages() >= 2);
    }

    /**
     * Test 10: Message length does not exceed 250 characters
     * Expected: Message text is 250 characters or less
     */
    @Test
    public void testMessageNotMoreThan250Characters() {
        // This message is within the 250 character limit
        String validMessage = "Hi Mike, can you join us for dinner tonight?";

        // Create message object
        Message message = new Message("+27718693002", validMessage);

        // Check that the message length is 250 or less
        assertTrue(message.getMessageText().length() <= 250);
    }
}