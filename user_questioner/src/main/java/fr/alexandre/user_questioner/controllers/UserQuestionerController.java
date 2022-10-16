package fr.alexandre.user_questioner.controllers;

import fr.alexandre.user_questioner.components.UserQuestioner;
import fr.alexandre.user_questioner.dao.User;
import fr.alexandre.user_questioner.errors.NotFoundException;
import fr.alexandre.user_questioner.exceptions.UserNotFoundException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserQuestionerController {

    @Autowired
    UserQuestioner userQuestioner;

    Logger logger = LoggerFactory.getLogger(UserQuestionerController.class);

    /**
     * GET: /user
     * Get user by email or get all users
     * @param email the email of the user, if null, return all users
     * @return
     */
    @ApiOperation(value = "Find all users or by email", notes = "Return users found")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Users found"),
    })
    @GetMapping("/user")
    public List<User> index(@Param("email") String email) {
        logger.trace("GET /user email=" + email);
        if(email == null) {
            List<User> users = this.userQuestioner.getAllUsers();
            logger.trace("GET /user: " + users.size() + " users found");
            return users;
        } else {
            try {
                List<User> users = List.of(this.userQuestioner.getUserByEmail(email));
                logger.trace("GET /user: " + users.size() + " users found");
                return users;
            } catch (UserNotFoundException e) {
                logger.trace("GET /user: user not found");
                return new ArrayList<>();
            }
        }
    }

    /**
     * GET: /user/{id}
     * Get user by id
     * @param id the id of the user
     * @return the user found
     * @throws NotFoundException if the user is not found
     */
    @ApiOperation(value = "Find user by id", notes = "Return user found")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User found"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id") String id) {
        logger.trace("GET /user/" + id);
        try {
            User user = this.userQuestioner.getUserById(id);
            logger.trace("GET /user/" + id + ": user found");
            return user;
        } catch (UserNotFoundException e) {
            logger.trace("GET /user/" + id + ": user not found");
            throw new NotFoundException(e.getMessage());
        }
    }
}
