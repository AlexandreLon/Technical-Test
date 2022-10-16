package fr.alexandre.user_registry.components.user_questioner;

import fr.alexandre.user_registry.dao.User;

import java.util.List;

public interface UserQuestioner {

    /**
     * Get user by email
     * @return a list of users with the same email
     * @param email the email of the user
     *              if the email is null, return an empty list
     */
    List<User> findUserByEmail(String email);
}
