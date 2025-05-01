package no.ntnu.webapp.repositories;

import no.ntnu.webapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // This method is used to find a user by their username
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    List<User> username(String username);
}
