package no.ntnu.webapp.controllers;
import no.ntnu.webapp.models.User;
import no.ntnu.webapp.repositories.UserRepository;
import no.ntnu.webapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

/**
 * UserController.java
 * This class handles user-related requests, including login, signup, and user dashboard.
 */
@Controller
public class UserController {


    private final UserService userService;
    private final UserRepository userRepository;

    /**
     * Constructor for UserController
     * @param userService Service for managing users
     * @param userRepository Repository for accessing user data
     */
    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }
    /**
     * Handles requests to the login page.
     * @return The name of the view to be rendered
     */

    @GetMapping("/login")
    public String LoginPage() {
        return "login";
    }

    /**
     * Handles requests to the signup page.
     * @return The name of the view to be rendered
     */
    @GetMapping("/signup")
    public String SignUpPage() {
        return "signup";
    }

    /**
     * Handles requests to the user dashboard.
     * @param model Model object to pass data to the view
     * @param principal Principal object representing the authenticated user
     * @return The name of the view to be rendered
     */
    @GetMapping("/user-dashboard")
    public String userDashboard(Model model, Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        model.addAttribute("user", user);
        return "user-dashboard";

    }

    /**
     * Handles user signup requests.
     * @param email User's email address
     * @param username User's username
     * @param password User's password
     * @param redirectAttributes Redirect attributes for flash messages
     * @return Redirects to the login page
     */
    @PostMapping("/signup")
    public String SignUp(
            @RequestParam String email,
            @RequestParam String username,
            @RequestParam String password,
            RedirectAttributes redirectAttributes) {

        // Check if the username already exists
        userService.registerUser(username, email, password);

        // Add a success message to the redirect attributes
        redirectAttributes.addFlashAttribute("success", "User registered successfully");
        return "redirect:/login";
    }
}
