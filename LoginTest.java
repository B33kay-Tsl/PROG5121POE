package prog5121poe;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * ================================================
 * PROG5121 - Part 1
 * Author    : Bonginkosi Dlamini
 * Student No: ST10511967
 * Date      : April 2026
 * Purpose   : This class contains all JUnit unit tests
 *             to verify that every method in the Login
 *             class works correctly with both valid
 *             and invalid test data.
 * ================================================
 */
public class LoginTest {
    
    
    @Test
    public void testUsernameCorrectlyFormatted() {
        Login login = new Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27821234567");
        
        assertTrue(login.checkUserName());
    }

    
    @Test
    public void testUsernameIncorrectlyFormatted() {
        Login login = new Login("Kyle", "Smith", "kyle!!!!!!!", "Ch&&sec@ke99!", "+27821234567");

        assertFalse(login.checkUserName());
    }

    @Test
    public void testPasswordMeetsComplexity() {
        Login login = new Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27821234567");

       
        assertTrue(login.checkPasswordComplexity());
    }

    @Test
    public void testPasswordDoesNotMeetComplexity() {
        Login login = new Login("Kyle", "Smith", "kyl_1", "password", "+27821234567");

        assertFalse(login.checkPasswordComplexity());
    }

    @Test
    public void testCellPhoneCorrectlyFormatted() {
        Login login = new Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27821234567");
     
        assertTrue(login.checkCellPhoneNumber());
    }

  
    @Test
    public void testCellPhoneIncorrectlyFormatted() {
        Login login = new Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "08966553");
    
        assertFalse(login.checkCellPhoneNumber());
    }

    @Test
    public void testLoginSuccessful() {
        Login login = new Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27821234567");
      
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginFailed() {        
        Login login = new Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27821234567");

        assertFalse(login.loginUser("kyl_1", "wrongpassword"));
    }

   
    @Test
    public void testRegisterUserSuccess() {       
        Login login = new Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27821234567");

        String expected = "Username successfully captured.\n"
                        + "Password successfully captured.\n"
                        + "Cell number successfully captured.";

       
        assertEquals(expected, login.registerUser());
    }

    
    @Test
    public void testRegisterUserBadUsername() {
        Login login = new Login("Kyle", "Smith", "kyle!!!!!!!", "Ch&&sec@ke99!", "+27821234567");

        
        assertEquals("Username is not correctly formatted; please ensure that your "
                   + "username contains an underscore and is no more than "
                   + "five characters in length.",
                   login.registerUser());
    }
}
