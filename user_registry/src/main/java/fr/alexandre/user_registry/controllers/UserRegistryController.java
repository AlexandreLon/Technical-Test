package fr.alexandre.user_registry.controllers;

import fr.alexandre.user_registry.components.user_registry.UserRegistry;
import fr.alexandre.user_registry.dao.User;
import fr.alexandre.user_registry.dto.UserDTO;
import fr.alexandre.user_registry.errors.BadRequestException;
import fr.alexandre.user_registry.errors.ConflictException;
import fr.alexandre.user_registry.errors.UnauthorizedException;
import fr.alexandre.user_registry.exceptions.UserAlreadyExistException;
import fr.alexandre.user_registry.exceptions.UserCountryRestrictionException;
import fr.alexandre.user_registry.exceptions.UserNotAdultException;
import fr.alexandre.user_registry.exceptions.ValidationException;
import fr.alexandre.user_registry.repositories.UserRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRegistryController {

    @Autowired
    private UserRegistry userRegistry;

    Logger logger = LoggerFactory.getLogger(UserRegistryController.class);

    /**
     * POST /user
     * @param userDTO the user to register
     * @return the user registered
     * @throws BadRequestException 400 if the user is not valid
     * @throws ConflictException 409 if the user already exist
     * @throws UnauthorizedException 401 if the user is not french or not adult
     */
    @ApiOperation(value = "Register a new user", notes = "Return user registered")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created, User registered"),
            @ApiResponse(code = 400, message = "Bad request, some fields are not valid"),
            @ApiResponse(code = 401, message = "Unauthorized, user is not french or not adult"),
            @ApiResponse(code = 409, message = "Conflict, user already exist")
    })
    @PostMapping("/user")
    public User registerUser(@RequestBody UserDTO userDTO) {
        logger.trace("POST /user " + userDTO.toString());
        try {
            User user = this.userRegistry.register(userDTO);
            logger.trace("POST /user: user " + user.toString() + " registered");
            return user;
        } catch (ValidationException e) {
            logger.error("POST /user: " + e.getMessage());
            throw new BadRequestException(e.toString());
        } catch (UserNotAdultException | UserCountryRestrictionException e) {
            logger.error("POST /user: " + e.getMessage());
            throw new UnauthorizedException(e.toString());
        } catch (UserAlreadyExistException e) {
            logger.error("POST /user: " + e.getMessage());
            throw new ConflictException(e.toString());
        }
    }
}
