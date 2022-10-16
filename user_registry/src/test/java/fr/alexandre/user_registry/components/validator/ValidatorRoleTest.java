package fr.alexandre.user_registry.components.validator;

import fr.alexandre.user_registry.dao.Role;
import fr.alexandre.user_registry.exceptions.ValidationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ValidatorRoleTest {

    @Autowired
    private FieldValidator validator;

    @Test
    public void testAdminValidRole() throws ValidationException {
        Role role = this.validator.isValidRole("ADMIN");
        assertEquals(Role.ADMIN, role);
    }

    @Test
    public void testGuestValidRole() throws ValidationException {
        Role role = this.validator.isValidRole("GUEST");
        assertEquals(Role.GUEST, role);
    }

    @Test
    public void testNullRole() throws ValidationException {
        Role role = this.validator.isValidRole(null);
        assertEquals(Role.GUEST, role);
    }

    @Test
    public void testEmptyRole() throws ValidationException {
        Role role = this.validator.isValidRole("");
        assertEquals(Role.GUEST, role);
    }

    @Test
    public void testInvalidRole() {
        assertThrows(ValidationException.class, () -> this.validator.isValidRole("USER"));
    }
}
