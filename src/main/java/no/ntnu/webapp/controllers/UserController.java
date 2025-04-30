import no.ntnu.webapp.models.User;
import no.ntnu.webapp.models.UserRole;
import no.ntnu.webapp.repositories.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@PostMapping("/signup")
public String signup(
        @RequestParam String email,
        @RequestParam String username,
        @RequestParam String password,
        RedirectAttributes redirectAttributes) {

    // Valider at ingen felt er tomme
    if (email == null || email.isEmpty()) {
        redirectAttributes.addFlashAttribute("error", "E-post er påkrevd");
        return "redirect:/signup";
    }
    if (username == null || username.isEmpty()) {
        redirectAttributes.addFlashAttribute("error", "Brukernavn er påkrevd");
        return "redirect:/signup";
    }
    if (password == null || password.isEmpty()) {
        redirectAttributes.addFlashAttribute("error", "Passord er påkrevd");
        return "redirect:/signup";
    }

    // Sjekk for eksisterende e-post eller brukernavn
    UserRepository userRepository = null;
    if (userRepository.findByEmail(email).isPresent()) {
        redirectAttributes.addFlashAttribute("error", "E-post er allerede i bruk");
        return "redirect:/signup";
    }
    if (userRepository.findByUsername(username).isPresent()) {
        redirectAttributes.addFlashAttribute("error", "Brukernavn er opptatt");
        return "redirect:/signup";
    }

    // Opprett bruker
    User user = new User();
    user.setEmail(email); // ⭐️ Sett e-post
    user.setUsername(username);
    user.setPassword(password); // Transient felt → hashes automatisk
    user.setRole(UserRole.ROLE_USER);

    userRepository.save(user);

    redirectAttributes.addFlashAttribute("success", "Bruker opprettet!");
    return "redirect:/login";
}