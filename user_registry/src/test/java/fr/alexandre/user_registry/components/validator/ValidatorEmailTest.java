package fr.alexandre.user_registry.components.validator;

import fr.alexandre.user_registry.exceptions.ValidationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ValidatorEmailTest {

    @Autowired
    private FieldValidator validator;

    @Test
    public void testValidEmail() throws ValidationException {
        String email = this.validator.isValidEmail("test@gmail.com");
        assertEquals("test@gmail.com", email);
    }

    @Test
    public void testSimpleTextInvalidEmail() {
        assertThrows(ValidationException.class, () -> this.validator.isValidEmail("test"));
    }

    @Test
    public void testNullEmail() {
        assertThrows(ValidationException.class, () -> this.validator.isValidEmail(null));
    }

    @Test
    public void testEmptyEmail() {
        assertThrows(ValidationException.class, () -> this.validator.isValidEmail(""));
    }

    @Test
    public void testEmailWithoutAt() {
        assertThrows(ValidationException.class, () -> this.validator.isValidEmail("testgmail.com"));
    }

    @Test
    public void testEmailWithoutDot() {
        assertThrows(ValidationException.class, () -> this.validator.isValidEmail("test@gmailcom"));
    }

    @Test
    public void testEmailWithoutDotAndAt() {
        assertThrows(ValidationException.class, () -> this.validator.isValidEmail("testgmailcom"));
    }

    @Test
    public void testEmailWithoutText() {
        assertThrows(ValidationException.class, () -> this.validator.isValidEmail("@gmailcom"));
    }

    @Test
    public void testEmailWithoutDomain() {
        assertThrows(ValidationException.class, () -> this.validator.isValidEmail("test@"));
    }

    @Test
    public void testEmailWithoutTextAndDomain() {
        assertThrows(ValidationException.class, () -> this.validator.isValidEmail("@"));
    }

    @Test
    public void testEmailWithInappropriateCharacters() {
        assertThrows(ValidationException.class, () -> this.validator.isValidEmail("test;@gmail.com"));
    }

    @Test
    public void testEmailWithInappropriateCharacters2() {
        assertThrows(ValidationException.class, () -> this.validator.isValidEmail("test@;gmail.com"));
    }
}
