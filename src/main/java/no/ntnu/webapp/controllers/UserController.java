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

@Controller
public class UserController {


    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String LoginPage() {
        return "login";
    }

    @GetMapping("/signup")
    public String SignUpPage() {
        return "signup";
    }
    @GetMapping("/user-dashboard")
    public String userDashboard(Model model, Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        model.addAttribute("user", user);
        return "user-dashboard";

    }




    @PostMapping("/signup")
    public String SignUp(
            @RequestParam String email,
            @RequestParam String username,
            @RequestParam String password,
            RedirectAttributes redirectAttributes) {

        userService.registerUser(username, email, password);


        redirectAttributes.addFlashAttribute("success", "User registered successfully");
        return "redirect:/login";
    }
}
