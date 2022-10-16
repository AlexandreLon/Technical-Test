package fr.alexandre.user_registry.components.user_questioner;

import fr.alexandre.user_registry.dao.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserQuestionerBean implements UserQuestioner {

    Logger logger = LoggerFactory.getLogger(UserQuestionerBean.class);

    public List<User> findUserByEmail(String email) {
        logger.trace("UserQuestionerBean.findUserByEmail email=" + email);
        RestTemplate restTemplate = new RestTemplate();

        String resourceUrl
                = "http://localhost:8081/api/user?email=" + email;

        // Fetch JSON response as String wrapped in ResponseEntity
        ResponseEntity<User[]> response
                = restTemplate.getForEntity(resourceUrl, User[].class);

        User[] users = response.getBody();
        if(users == null) {
            logger.error("UserQuestionerBean.findUserByEmail: user not found");
            return new ArrayList<>();
        }
        logger.trace("UserQuestionerBean.findUserByEmail: " + users.length + " users found");
        return List.of(users);
    }
}
