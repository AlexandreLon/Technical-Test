package fr.alexandre.user_registry.components.validator;

import fr.alexandre.user_registry.dao.Role;
import fr.alexandre.user_registry.exceptions.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ValidatorBean implements FieldValidator {
    Logger logger = LoggerFactory.getLogger(ValidatorBean.class);

    @Override
    public String isValidEmail(String email) throws ValidationException {
        logger.trace("ValidatorBean.isValidEmail email=" + email);
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        if(email != null && email.matches(ePattern)) {
            logger.trace("ValidatorBean.isValidEmail: email is valid");
            return email;
        }
        logger.error("ValidatorBean.isValidEmail: email is not valid");
        throw new ValidationException("Error: Email is not valid");
    }

    @Override
    public String isValidName(String username) throws ValidationException {
        logger.trace("ValidatorBean.isValidName username=" + username);
        String nPattern = "^[a-zA-Z ,.'-]+$";
        if(username != null && username.matches(nPattern))
        {
            logger.trace("ValidatorBean.isValidName: username is valid");
            return username;
        }
        logger.error("ValidatorBean.isValidName: username is not valid");
        throw new ValidationException("Error: Name is not valid");
    }

    @Override
    public String isValidPassword(String password) throws ValidationException {
        logger.trace("ValidatorBean.isValidPassword password=" + password);
        String pPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
        if (password != null && password.matches(pPattern)) {
            logger.trace("ValidatorBean.isValidPassword: password is valid");
            return password;
        }
        logger.error("ValidatorBean.isValidPassword: password is not valid");
        throw new ValidationException("Error: Password is not valid, it must contains one uppercase char, one lowercase and one digit and eight characters");
    }


    @Override
    public Role isValidRole(String role) throws ValidationException {
        logger.trace("ValidatorBean.isValidRole role=" + role);
        if(role == null || role.equals("") || role.equals("GUEST"))
        {
            logger.trace("ValidatorBean.isValidRole: role is valid");
            return Role.GUEST;
        }
        else if(role.equals("ADMIN"))
        {
            logger.trace("ValidatorBean.isValidRole: role is valid");
            return Role.ADMIN;
        }
        logger.error("ValidatorBean.isValidRole: role is not valid");
        throw new ValidationException("Error: Undefined role");
    }

    @Override
    public void isSamePassword(String password, String confirmPassword) throws ValidationException {
        logger.trace("ValidatorBean.isSamePassword password=" + password + " confirmPassword=" + confirmPassword);
        if(password == null || !password.equals(confirmPassword)) {
            logger.error("ValidatorBean.isSamePassword: password and confirm password are not the same");
            throw new ValidationException("Error: the passwords are different");
        }
        logger.trace("ValidatorBean.isSamePassword: password and confirm password are the same");
    }

    @Override
    public LocalDate isValidBirthdate(LocalDate birthdate) throws ValidationException {
        logger.trace("ValidatorBean.isValidBirthdate birthdate=" + birthdate);
        if(birthdate != null && birthdate.isBefore(LocalDate.now()))
        {
            logger.trace("ValidatorBean.isValidBirthdate: birthdate is valid");
            return birthdate;
        }
        logger.error("ValidatorBean.isValidBirthdate: birthdate is not valid");
        throw new ValidationException("Error: Birthdate is not valid");
    }

    @Override
    public String isValidPhoneNumber(String phoneNumber) throws ValidationException {
        logger.trace("ValidatorBean.isValidPhoneNumber phoneNumber=" + phoneNumber);
        String pPattern = "^(\\+33|0)[1-9](\\d{2}){4}$";
        if(phoneNumber == null || phoneNumber.equals("") || phoneNumber.matches(pPattern))
        {
            logger.trace("ValidatorBean.isValidPhoneNumber: phoneNumber is valid");
            return phoneNumber;
        }
        logger.error("ValidatorBean.isValidPhoneNumber: phoneNumber is not valid");
        throw new ValidationException("Error: Phone number is not valid");
    }
}
