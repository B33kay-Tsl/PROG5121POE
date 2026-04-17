package prog5121poe;

/**
 * ================================================
 * PROG5121 - Part 1
 * Author    : Bonginkosi Dlamini
 * Student No: ST10511967
 * Date      : April 2026
 * Purpose   : This class handles all user registration
 *             and login functionality including
 *             validation of username, password,
 *             and cell phone number.
 * ================================================
 */
public class Login {

    // ── Private Fields ────────────────────────────
    // These variables store the user's information
    // They are private so only this class can access them directly
    private String firstName; 
    private String lastName;   
    private String username;   
    private String password;    
    private String cellNumber;  

    // ── Constructor ───────────────────────────────
    
    public Login(String firstName, String lastName,
                 String username, String password,
                 String cellNumber) {
        this.firstName  = firstName; 
        this.lastName   = lastName;   
        this.username   = username; 
        this.password   = password;    
        this.cellNumber = cellNumber;  
    }

    // ═════════════════════════════════════════════
    // VALIDATION METHODS
    // These methods check if the user input meets
    // the required format rules
    // ═════════════════════════════════════════════

    public boolean checkUserName() {
       
        boolean hasUnderscore = username.contains("_");

        boolean correctLength = username.length() <= 5;
        
        return hasUnderscore && correctLength;
    }

   //password complexity
    public boolean checkPasswordComplexity() {
        // First check - if password is shorter than 8 characters, immediately return false
        if (password.length() < 8) {
            return false; // Password too short, no need to check further
        }

        // These flags track whether each requirement has been found
        boolean hasCapital = false; 
        boolean hasNumber  = false; 
        boolean hasSpecial = false; 

        // Loop through every single character in the password one by one
        for (char character : password.toCharArray()) {

            if (Character.isUpperCase(character)) {
                hasCapital = true;

            } else if (Character.isDigit(character)) {
                hasNumber = true;

            } else if (!Character.isLetterOrDigit(character)) {
                hasSpecial = true;
            }
        }

        // Password is only valid if ALL three requirements were found
        return hasCapital && hasNumber && hasSpecial;
    }

   //checks cellphone number + country code
    public boolean checkCellPhoneNumber() {
        
        String pattern = "^\\+\\d{1,3}\\d{7,10}$";
        
        return cellNumber.matches(pattern);
    }

    // ═════════════════════════════════════════════
    // REGISTRATION METHOD
    // ═════════════════════════════════════════════
    
    public String registerUser() {
        // Run all three validation checks and store the results
        boolean validUsername = checkUserName();       
        boolean validPassword = checkPasswordComplexity(); 
        boolean validCell     = checkCellPhoneNumber(); 

        // Step 1: Check username - if invalid, return error message immediately
        if (!validUsername) {
            return "Username is not correctly formatted; please ensure that your "
                 + "username contains an underscore and is no more than "
                 + "five characters in length.";
        }

        // Step 2: Check password - if invalid, return error message immediately
        if (!validPassword) {
            return "Password is not correctly formatted; please ensure that the "
                 + "password contains at least eight characters, a capital "
                 + "letter, a number, and a special character.";
        }

        // Step 3: Check cell number - if invalid, return error message immediately
        if (!validCell) {
            return "Cell phone number incorrectly formatted or does not "
                 + "contain international code.";
        }

        // All three validations passed - return success messages
        return "Username successfully captured.\n"     
             + "Password successfully captured.\n"   
             + "Cell number successfully captured.";
    }

    // ═════════════════════════════════════════════
    // LOGIN METHODS
    // ═════════════════════════════════════════════
    public boolean loginUser(String inputUsername, String inputPassword) {
      
        boolean usernameMatch = this.username.equals(inputUsername);

        boolean passwordMatch = this.password.equals(inputPassword);
        return usernameMatch && passwordMatch;
    }

//returns the appropriate login status message
    public String returnLoginStatus(String inputUsername, String inputPassword) {
        boolean loginSuccess = loginUser(inputUsername, inputPassword);

        if (loginSuccess) {
            return "Welcome " + firstName + " " + lastName
                 + " it is great to see you.";
        }

        return "Username or password incorrect, please try again.";
    }

    // ═════════════════════════════════════════════
    // GETTER METHODS
    // ═════════════════════════════════════════════
    public String fetchUsername()  { return username; }

    public String fetchPassword()  { return password; }

    public String fetchFirstName() { return firstName; }

    public String fetchLastName()  { return lastName; }
}
