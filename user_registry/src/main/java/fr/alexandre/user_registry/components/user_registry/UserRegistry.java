package fr.alexandre.user_registry.components.user_registry;

import fr.alexandre.user_registry.dao.User;
import fr.alexandre.user_registry.dto.UserDTO;
import fr.alexandre.user_registry.exceptions.UserAlreadyExistException;
import fr.alexandre.user_registry.exceptions.UserCountryRestrictionException;
import fr.alexandre.user_registry.exceptions.UserNotAdultException;
import fr.alexandre.user_registry.exceptions.ValidationException;

public interface UserRegistry {
    /**
     * Register a new user
     * @param user the user to register
     * @return the user registered
     * @throws UserAlreadyExistException if the user already exist
     * @throws UserCountryRestrictionException if the user is not french
     * @throws UserNotAdultException if the user is not adult
     * @throws ValidationException if the user is not valid
     */
    User register(UserDTO user) throws ValidationException, UserAlreadyExistException, UserNotAdultException, UserCountryRestrictionException;
}
