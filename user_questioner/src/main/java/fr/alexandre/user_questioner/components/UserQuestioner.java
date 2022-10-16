package fr.alexandre.user_questioner.components;

import fr.alexandre.user_questioner.dao.User;
import fr.alexandre.user_questioner.exceptions.UserNotFoundException;

import java.util.List;

public interface UserQuestioner {
    /**
     * Get all the users
     * @return a list of users
     */
    List<User> getAllUsers();

    /**
     * Get a user by his email
     * @param email the email of the user
     * @return the user
     * @throws UserNotFoundException if the user is not found
     */
    User getUserByEmail(String email) throws UserNotFoundException;

    /**
     * Get a user by his id
     * @param id the id of the user
     * @return the user
     * @throws UserNotFoundException if the user is not found
     */
    User getUserById(String id) throws UserNotFoundException;
}
