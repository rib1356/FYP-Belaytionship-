package rob_pc.fypbelaytionship;

import org.junit.Test;

import static org.junit.Assert.*;

public class LoginActivityTest {

    @Test //When both email and password have passed validation the method returns true which is checked against
    public void emailValidator_CorrectEmailSimple_ReturnsTrue() {
        LoginActivity loginActivity = new LoginActivity();
        boolean result = loginActivity.emailValidation("test@test.com", "testtest");
        boolean expected = true;
        assertEquals(result, expected);
    }

    @Test //If the email is empty this means the validation wont work and returns false
    public void emailValidator_IncorrectEmail_ReturnsFalse() {
        LoginActivity loginActivity = new LoginActivity();
        boolean result = loginActivity.emailValidation("", "testtest");
        boolean expected = false;
        assertEquals(result, expected);
    }

    @Test //If the password is empty this means the validation wont work and returns false
    public void emailValidator_IncorrectPassword_ReturnsFalse() {
        LoginActivity loginActivity = new LoginActivity();
        boolean result = loginActivity.emailValidation("test@test.com", "");
        boolean expected = false;
        assertEquals(result, expected);
    }

    @Test //If the password is too short (>6 characters) it will return false
    public void emailValidator_TooShortPassword_ReturnsFalse() {
        LoginActivity loginActivity = new LoginActivity();
        boolean result = loginActivity.emailValidation("test@test.com", "test");
        boolean expected = false;
        assertEquals(result, expected);
    }


}