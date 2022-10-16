package fr.alexandre.user_registry.components.validator;

import fr.alexandre.user_registry.exceptions.ValidationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ValidatorSamePasswordTest {

    @Autowired
    private FieldValidator validator;

    @Test
    public void testSamePassword() {
        assertDoesNotThrow(() -> this.validator.isSamePassword("password", "password"));
    }

    @Test
    public void testDifferentPassword() {
        assertThrows(ValidationException.class, () -> this.validator.isSamePassword("password", "password1"));
    }

    @Test
    public void testNullPassword() {
        assertThrows(ValidationException.class, () -> this.validator.isSamePassword(null, "password"));
    }

    @Test
    public void testEmptyPassword() {
        assertThrows(ValidationException.class, () -> this.validator.isSamePassword("", "password"));
    }

    @Test
    public void testNullConfirmPassword() {
        assertThrows(ValidationException.class, () -> this.validator.isSamePassword("password", null));
    }

    @Test
    public void testEmptyConfirmPassword() {
        assertThrows(ValidationException.class, () -> this.validator.isSamePassword("password", ""));
    }

    @Test
    public void testNullPasswordAndConfirmPassword() {
        assertThrows(ValidationException.class, () -> this.validator.isSamePassword(null, null));
    }

    @Test
    public void testEmptyPasswordAndConfirmPassword() {
        assertDoesNotThrow(() -> this.validator.isSamePassword("", ""));
    }
}
