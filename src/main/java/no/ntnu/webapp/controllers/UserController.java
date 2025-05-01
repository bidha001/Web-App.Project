package no.ntnu.webapp.controllers;


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
    public String LoginPage() {
        return "login";
    }

    @GetMapping("/signup")
    public String SignUpPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public String SignUp(
            @RequestParam String email,
            @RequestParam String username,
            @RequestParam String password,
            RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("success", "User registered successfully");
        return "redirect:/login";
    }
}
