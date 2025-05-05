package no.ntnu.webapp.controllers;


import no.ntnu.webapp.models.User;
import no.ntnu.webapp.models.UserRole;
import no.ntnu.webapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/signup")
    public String showSignupPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(
            @RequestParam String email,
            @RequestParam String username,
            @RequestParam String password,
            RedirectAttributes redirectAttributes) {


        if (email.isEmpty() || username.isEmpty() || password.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "All fields must be filled in");
            return "redirect:/signup";
        }


        if (userRepository.findByEmail(email).isPresent()) {
            redirectAttributes.addFlashAttribute("error", "E-post are already in use");
            return "redirect:/signup";
        }
        if (userRepository.findByUsername(username).isPresent()) {
            redirectAttributes.addFlashAttribute("error", "Username is already in use");
            return "redirect:/signup";
        }

        // Create a new user
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPasswordHash(passwordEncoder.encode(password));
        user.setRole(UserRole.REGISTERED); // Set the role to REGISTERED for new users

        userRepository.save(user);

        redirectAttributes.addFlashAttribute("success", "User created successfully");
        return "redirect:/login";
    }
}