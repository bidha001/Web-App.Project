package no.ntnu.webapp.services;

import no.ntnu.webapp.models.User;
import no.ntnu.webapp.models.UserRole;
import no.ntnu.webapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(UserRole.ROLE_USER); // Set default role to USER
        user.setRole(UserRole.ROLE_ADMIN); // Set default role to REGISTERED
        userRepository.save(user);

    }
}
