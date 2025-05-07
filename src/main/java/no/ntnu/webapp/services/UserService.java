package no.ntnu.webapp.services;


import no.ntnu.webapp.models.User;
import no.ntnu.webapp.models.UserRole;
import no.ntnu.webapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(String username, String email, String password) {
        // Check if the username or email are empty
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        // Check if the password is empty
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }

        // Validate the username and email format




        // Check if the username or email already exists in the database
        Optional<User> existingByUsername = userRepository.findByUsername(username);
        if (existingByUsername.isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }
        Optional<User> existingByEmail = userRepository.findByEmail(email);
        if (existingByEmail.isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }



            validatePassword(password);


            // Create a new user and ensure the password is hashed
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPasswordHash(passwordEncoder.encode(password));
            user.setRole(UserRole.REGISTERED);

            // Save the user information in the database
            userRepository.save(user);
        }

        // Validate that the username contains only letters
    private void validateUsername (String username) {
        if (!username.matches("^[a-zA-Z]+$")) {
            throw new IllegalArgumentException("Username can only contain letters");
        }
    }

    // Validate that the email is in a valid format

    private void validateEmail(String email) {
        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("Email must be in a valid format (d.t., example@gmail.com)");
        }
    }

    // Check if the password is strong enough
    private void validatePassword (String password) {

        if (password.length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters long");
        }
        // Check if the password contains at least one letter and one number
        if (!password.matches(".*[a-zA-Z].*") || !password.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Password must contain both letters and numbers");
        }
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
}




