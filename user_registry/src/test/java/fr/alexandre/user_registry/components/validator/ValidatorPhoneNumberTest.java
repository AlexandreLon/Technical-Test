package fr.alexandre.user_registry.components.validator;

import fr.alexandre.user_registry.exceptions.ValidationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ValidatorPhoneNumberTest {

    @Autowired
    private FieldValidator validator;

    @Test
    public void testValidPhoneNumber() throws ValidationException {
        String phoneNumber = this.validator.isValidPhoneNumber("0123456789");
        assertEquals("0123456789", phoneNumber);
    }

    @Test
    public void testValidMobilePhoneNumber() throws ValidationException {
        String phoneNumber = this.validator.isValidPhoneNumber("0612345678");
        assertEquals("0612345678", phoneNumber);
    }

    @Test
    public void testValidWithCountryCodePhoneNumber() throws ValidationException {
        String phoneNumber = this.validator.isValidPhoneNumber("+33123456789");
        assertEquals("+33123456789", phoneNumber);
    }

    @Test
    public void testNullPhoneNumber() {
        assertDoesNotThrow(() -> this.validator.isValidPhoneNumber(null));
    }

    @Test
    public void testEmptyPhoneNumber() {
        assertDoesNotThrow(() -> this.validator.isValidPhoneNumber(""));
    }

    @Test
    public void testInvalidPhoneNumber() {
        assertThrows(ValidationException.class, () -> this.validator.isValidPhoneNumber("99999999999999"));
    }

    @Test
    public void testInvalidPhoneNumber2() {
        assertThrows(ValidationException.class, () -> this.validator.isValidPhoneNumber("azerty"));
    }

    @Test
    public void testInvalidPhoneNumber3() {
        assertThrows(ValidationException.class, () -> this.validator.isValidPhoneNumber("0123456789a"));
    }

    @Test
    public void testInvalidPhoneNumber4() {
        assertThrows(ValidationException.class, () -> this.validator.isValidPhoneNumber("0123456789 "));
    }

    @Test
    public void testInvalidPhoneNumber5() {
        assertThrows(ValidationException.class, () -> this.validator.isValidPhoneNumber(" 0123456789"));
    }
}
