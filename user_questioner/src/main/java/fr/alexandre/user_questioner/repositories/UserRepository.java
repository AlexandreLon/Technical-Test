package fr.alexandre.user_questioner.repositories;

import fr.alexandre.user_questioner.dao.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
}
