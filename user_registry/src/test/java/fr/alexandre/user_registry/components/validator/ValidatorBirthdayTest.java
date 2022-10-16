package fr.alexandre.user_registry.components.validator;

import fr.alexandre.user_registry.exceptions.ValidationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ValidatorBirthdayTest {

    @Autowired
    private FieldValidator validator;

    @Test
    public void testValidBirthday() throws ValidationException {
        LocalDate birthday = this.validator.isValidBirthdate(LocalDate.of(2000, 1, 1));
        assertEquals(LocalDate.of(2000, 1, 1), birthday);
    }

    @Test
    public void testNullBirthday() {
        assertThrows(ValidationException.class, () -> this.validator.isValidBirthdate(null));
    }

    @Test
    public void testFutureBirthday() {
        assertThrows(ValidationException.class, () -> this.validator.isValidBirthdate(LocalDate.now().plusDays(1)));
    }
}
