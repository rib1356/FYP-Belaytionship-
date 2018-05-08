package rob_pc.fypbelaytionship;

import org.junit.Test;

import static org.junit.Assert.*;

public class RegisterActivityTest {

    @Test //When both email and password have passed validation the method returns true which is checked against
    public void emailValidator_CorrectEmailSimple_ReturnsTrue() {
        RegisterActivity registerActivity = new RegisterActivity();
        boolean result = registerActivity.emailValidation("test@test.com", "testtest");
        boolean expected = true;
        assertEquals(result, expected);
    }

    @Test //If the email is empty this means the validation wont work and returns false
    public void emailValidator_IncorrectEmail_ReturnsFalse() {
        RegisterActivity registerActivity = new RegisterActivity();
        boolean result = registerActivity.emailValidation("", "testtest");
        boolean expected = false;
        assertEquals(result, expected);
    }

    @Test //If the password is empty this means the validation wont work and returns false
    public void emailValidator_IncorrectPassword_ReturnsFalse() {
        RegisterActivity registerActivity = new RegisterActivity();
        boolean result = registerActivity.emailValidation("test@test.com", "");
        boolean expected = false;
        assertEquals(result, expected);
    }

    @Test //If the password is too short (password<6 characters) it will return false
    public void emailValidator_TooShortPassword_ReturnsFalse() {
        RegisterActivity registerActivity = new RegisterActivity();
        boolean result = registerActivity.emailValidation("test@test.com", "test");
        boolean expected = false;
        assertEquals(result, expected);
    }


}