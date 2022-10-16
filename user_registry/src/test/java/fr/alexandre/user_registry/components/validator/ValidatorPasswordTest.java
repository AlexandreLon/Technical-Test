package fr.alexandre.user_registry.components.validator;

import fr.alexandre.user_registry.exceptions.ValidationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ValidatorPasswordTest {

    @Autowired
    private FieldValidator validator;

    @Test
    public void testValidPassword() throws ValidationException {
        String password = this.validator.isValidPassword("P@ssw0rd1");
        assertEquals("P@ssw0rd1", password);
    }

    @Test
    public void testNullPassword() {
        assertThrows(ValidationException.class, () -> this.validator.isValidPassword(null));
    }

    @Test
    public void testEmptyPassword() {
        assertThrows(ValidationException.class, () -> this.validator.isValidPassword(""));
    }

    @Test
    public void testPasswordTooShort() {
        assertThrows(ValidationException.class, () -> this.validator.isValidPassword("P@ss"));
    }

    @Test
    public void testPasswordWithoutUpperCase() {
        assertThrows(ValidationException.class, () -> this.validator.isValidPassword("p@ssw0rd1"));
    }

    @Test
    public void testPasswordWithoutLowerCase() {
        assertThrows(ValidationException.class, () -> this.validator.isValidPassword("P@SSW0RD1"));
    }

    @Test
    public void testPasswordWithoutNumber() {
        assertThrows(ValidationException.class, () -> this.validator.isValidPassword("P@ssword"));
    }

    @Test
    public void testPasswordWithoutSpecialCharacter() {
        assertThrows(ValidationException.class, () -> this.validator.isValidPassword("Passw0rd1"));
    }
}
