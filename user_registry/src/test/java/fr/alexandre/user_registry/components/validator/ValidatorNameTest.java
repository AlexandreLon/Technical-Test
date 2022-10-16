package fr.alexandre.user_registry.components.validator;

import fr.alexandre.user_registry.exceptions.ValidationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ValidatorNameTest {

    @Autowired
    private FieldValidator validator;

    @Test
    public void testValidName() throws ValidationException {
        String name = this.validator.isValidName("John");
        assertEquals("John", name);
    }

    @Test
    public void testSpaceName() throws ValidationException {
        String name = this.validator.isValidName("John Doe");
        assertEquals("John Doe", name);
    }

    @Test
    public void testApostropheName() throws ValidationException {
        String name = this.validator.isValidName("Doe's");
        assertEquals("Doe's", name);
    }

    @Test
    public void testDotName() throws ValidationException {
        String name = this.validator.isValidName("Doe, Jr.");
        assertEquals("Doe, Jr.", name);
    }

    @Test
    public void testNullName() {
        assertThrows(ValidationException.class, () -> this.validator.isValidName(null));
    }

    @Test
    public void testEmptyName() {
        assertThrows(ValidationException.class, () -> this.validator.isValidName(""));
    }

    @Test
    public void testNumberName() {
        assertThrows(ValidationException.class, () -> this.validator.isValidName("John1"));
    }

    @Test
    public void testSpecialCharacterName() {
        assertThrows(ValidationException.class, () -> this.validator.isValidName("John!"));
    }

}
