package fr.alexandre.user_registry.components.user_registry;

import fr.alexandre.user_registry.components.age_calculator.AgeCalculator;
import fr.alexandre.user_registry.components.user_questioner.UserQuestioner;
import fr.alexandre.user_registry.components.validator.FieldValidator;
import fr.alexandre.user_registry.dao.Role;
import fr.alexandre.user_registry.dao.User;
import fr.alexandre.user_registry.dto.UserDTO;
import fr.alexandre.user_registry.exceptions.UserAlreadyExistException;
import fr.alexandre.user_registry.exceptions.UserCountryRestrictionException;
import fr.alexandre.user_registry.exceptions.UserNotAdultException;
import fr.alexandre.user_registry.exceptions.ValidationException;
import fr.alexandre.user_registry.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class UserRegistryBean implements UserRegistry {

    Logger logger = LoggerFactory.getLogger(UserRegistryBean.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FieldValidator fieldValidator;

    @Autowired
    private AgeCalculator ageCalculator;

    @Autowired
    private UserQuestioner userQuestioner;

    @Override
    public User register(UserDTO userDTO) throws ValidationException, UserAlreadyExistException, UserNotAdultException, UserCountryRestrictionException {
        logger.trace("UserRegistryBean.register userDTO=" + userDTO);
        String email = this.fieldValidator.isValidEmail(userDTO.email);
        String nickname = this.fieldValidator.isValidName(userDTO.nickname);
        String firstname = this.fieldValidator.isValidName(userDTO.firstname);
        String lastname = this.fieldValidator.isValidName(userDTO.lastname);
        String password = this.fieldValidator.isValidPassword(userDTO.password);
        String phoneNumber = this.fieldValidator.isValidPhoneNumber(userDTO.phoneNumber);
        this.fieldValidator.isSamePassword(userDTO.password, userDTO.confirmPassword);
        Role role = this.fieldValidator.isValidRole(userDTO.role);

        LocalDate birthdate = this.fieldValidator.isValidBirthdate(userDTO.birthdate);

        List<User> users = this.userQuestioner.findUserByEmail(email);

        if(users.size() > 0) {
            logger.error("UserRegistryBean.register: user already exist");
            throw new UserAlreadyExistException("User already exist");
        }

        if(this.ageCalculator.calculateAge(userDTO.birthdate) < 18) {
            logger.error("UserRegistryBean.register: user is not adult");
            throw new UserNotAdultException("Error: You must be 18 years old");
        }

        if(!userDTO.countryCode.equals("FR")) {
            logger.error("UserRegistryBean.register: user is not from France");
            throw new UserCountryRestrictionException("Error: You must be French");
        }

        User user = new User(firstname, lastname, birthdate, email, password, nickname, userDTO.address, phoneNumber, userDTO.countryCode, role);
        userRepository.save(user);
        logger.trace("UserRegistryBean.register: " + user);
        return user;
    }
}
