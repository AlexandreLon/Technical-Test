package fr.alexandre.user_questioner.components;

import fr.alexandre.user_questioner.dao.User;
import fr.alexandre.user_questioner.exceptions.UserNotFoundException;
import fr.alexandre.user_questioner.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserQuestionerBean implements UserQuestioner {

    @Autowired
    private UserRepository userRepository;

    Logger logger = LoggerFactory.getLogger(UserQuestionerBean.class);

    @Override
    public List<User> getAllUsers() {
        logger.trace("UserQuestionerBean.GetAllUsers");
        List<User> users = userRepository.findAll();
        logger.trace("UserQuestionerBean.GetAllUsers: " + users.size() + " users found");
        return users;
    }

    @Override
    public User getUserByEmail(String email) throws UserNotFoundException {
        logger.trace("UserQuestionerBean.getUserByEmail email=" + email);
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            logger.warn("UserQuestionerBean.getUserByEmail: user not found");
            throw new UserNotFoundException("User not found");
        }
        logger.trace("UserQuestionerBean.getUserByEmail: " + user);
        return user;
    }

    @Override
    public User getUserById(String id) throws UserNotFoundException {
        logger.trace("UserQuestionerBean.getUserById id=" + id);
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            logger.error("UserQuestionerBean.getUserById: user not found");
            throw new UserNotFoundException("User not found");
        }
        logger.trace("UserQuestionerBean.getUserById: " + user);
        return user;
    }
}
