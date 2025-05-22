package no.ntnu.webapp.repositories;

import no.ntnu.webapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for User entity.
 * This interface extends JpaRepository to provide CRUD operations for User entities.
 */

public interface UserRepository extends JpaRepository<User, Long> {
    // This method is used to find a user by their username
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

}
