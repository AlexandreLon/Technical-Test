package fr.alexandre.user_registry.repositories;

import fr.alexandre.user_registry.dao.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
}
