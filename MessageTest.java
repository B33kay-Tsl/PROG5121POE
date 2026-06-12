package prog5121poe;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * PROG5121 - Unit Tests for Parts 2 & 3
 * Author: Bonginkosi Dlamini
 * Date: April 2026
 */
public class MessageTest {

    // Test instances tracking mock lifecycle properties
    private Message testMsg1;  // Sent
    private Message testMsg2;  // Stored (Longest message entry)
    private Message testMsg3;  // Disregarded
    private Message testMsg4;  // Sent
    private Message testMsg5;  // Stored

    /**
     * Reinitializes memory fields and mocks data payloads before every isolated test execution.
     */
    @Before
    public void setUp() {
        Message.resetAll();

        // 1: Sent Message Payload
        testMsg1 = new Message("+27834537896", "Did you get the cake?");
        testMsg1.SentMessage(1);

        // 2: Stored Message Payload (Longest Entry)
        testMsg2 = new Message("+27839884567", "Where are you? You are late! I have asked you to be on time.");
        testMsg2.SentMessage(3);

        // 3: Disregarded Message Payload
        testMsg3 = new Message("+27834485567", "Yohooo, I am at your gate");
        testMsg3.SentMessage(2);

        // 4: Sent Message Payload (Bypasses regular expression check)
        testMsg4 = new Message("0638884567", "It is dinner time");
        testMsg4.SentMessage(1);

        // 5: Stored Message Payload
        testMsg5 = new Message("+27838884567", "Ok, I am leaving without you");
        testMsg5.SentMessage(3);
    }

    // ── PART 2 METHOD TESTING ─────────────────────────────────────

    @Test
    public void testMessageIDNotMoreThanTenCharacters() {
        Message message = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertTrue(message.checkMessageID());
    }

    @Test
    public void testRecipientCellCorrectlyFormatted() {
        Message message = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertTrue(message.checkRecipientCell());
    }

    @Test
    public void testRecipientCellIncorrectlyFormatted() {
        Message message = new Message("08966553", "Hi Mike, can you join us for dinner tonight?");
        assertFalse(message.checkRecipientCell());
    }

    @Test
    public void testMessageHashCreated() {
        Message message = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertNotNull(message.getMessageHash());
        assertFalse(message.getMessageHash().isEmpty());
    }

    @Test
    public void testMessageHashIsUpperCase() {
        Message message = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?");
        String hash = message.getMessageHash();
        assertEquals(hash, hash.toUpperCase());
    }

    @Test
    public void testMessageSuccessfullySent() {
        Message message = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message successfully sent.", message.SentMessage(1));
    }

    @Test
    public void testMessageSuccessfullyDisregarded() {
        Message message = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message successfully disregarded.", message.SentMessage(2));
    }

    @Test
    public void testMessageSuccessfullyStored() {
        Message message = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message successfully stored.", message.SentMessage(3));
    }

    @Test
    public void testReturnTotalMessagesSent() {
        // Quantifies that testMsg1 and testMsg4 increments tracker to >= 2
        assertTrue(testMsg4.returnTotalMessages() >= 2);
    }

    @Test
    public void testMessageNotMoreThan250Characters() {
        Message message = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertTrue(message.getMessageText().length() <= 250);
    }

    // ── PART 3 METHOD TESTING ─────────────────────────────────────

    @Test
    public void testSentMessagesArrayCorrectlyPopulated() {
        assertEquals("Did you get the cake?", Message.getSentMessagesArray()[0]);
        assertEquals("It is dinner time", Message.getSentMessagesArray()[1]);
    }

    @Test
    public void testDisplayLongestMessage() {
        String result = Message.returnSentMessages();
        assertTrue(result.contains("Did you get the cake?"));
        assertTrue(result.contains("It is dinner time"));
    }

    @Test
    public void testSearchByMessageID() {
        String msg4ID = testMsg4.getMessageID();
        String result = Message.searchByMessageID(msg4ID);
        assertEquals("It is dinner time", result);
    }

    @Test
    public void testSearchByRecipient() {
        String result = Message.searchByRecipient("+27839884567");
        assertEquals("Where are you? You are late! I have asked you to be on time.", result);
    }

    @Test
    public void testDeleteMessageByHash() {
        String msg2Hash = testMsg2.getMessageHash();
        String result = Message.deleteByHash(msg2Hash);
        assertTrue(result.contains("successfully deleted"));
    }

    @Test
    public void testDisplayReport() {
        String report = Message.displayReport();
        assertTrue(report.contains("Where are you? You are late! I have asked you to be on time."));
        assertTrue(report.contains("Ok, I am leaving without you"));
    }

    @Test
    public void testGetLongestStoredMessage() {
        assertEquals("Where are you? You are late! I have asked you to be on time.", Message.getLongestMessage());
    }
}
