package fr.alexandre.user_registry.components.validator;

import fr.alexandre.user_registry.dao.Role;
import fr.alexandre.user_registry.exceptions.ValidationException;

import java.time.LocalDate;

public interface FieldValidator {
    /**
     * Validate a correct email format
     * @param email the email to validate
     * @return email if valid
     * @throws ValidationException if email is not valid
     */
    String isValidEmail(String email) throws ValidationException;

    /**
     * Validate a correct name format
     * The name must be set
     * @param username the value of the field
     * @return username if valid
     * @throws ValidationException if username is not valid
     */
    String isValidName(String username) throws ValidationException;

    /**
     * Validate a correct password
     * A password must contain at least one uppercase char, one lowercase, one special char, and one digit and eight characters
     * @param password the password to validate
     * @return password if valid
     * @throws ValidationException if password is not valid
     */
    String isValidPassword(String password) throws ValidationException;

    /**
     * Validate a correct role
     * @param role the role to validate
     * @return role if valid
     * @throws ValidationException if role is not valid
     */
    Role isValidRole(String role) throws ValidationException;

    /**
     * Validate if two passwords are the same
     * @param password the first password
     * @param confirmPassword the second password
     * @throws ValidationException if passwords are not the same
     */
    void isSamePassword(String password, String confirmPassword) throws ValidationException;

    /**
     * Validate a correct birth date
     * A birth date must be in the past
     * @param birthdate the birth date to validate
     * @return birthDate if valid
     * @throws ValidationException if birthDate is not valid
     */
    LocalDate isValidBirthdate(LocalDate birthdate) throws ValidationException;

    /**
     * Validate a correct phone number
     * @param phoneNumber the phone number to validate
     * The phone number must be in the format +33 6 00 00 00 00
     * @return phoneNumber if valid
     * @throws ValidationException if phoneNumber is not valid
     */
    String isValidPhoneNumber(String phoneNumber) throws ValidationException;
}
