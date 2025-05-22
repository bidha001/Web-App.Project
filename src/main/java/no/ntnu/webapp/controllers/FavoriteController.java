package no.ntnu.webapp.controllers;

import no.ntnu.webapp.models.Bookmark;
import no.ntnu.webapp.models.Course;
import no.ntnu.webapp.models.User;
import no.ntnu.webapp.services.BookmarkService;
import no.ntnu.webapp.services.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/favorites")
public class FavoriteController {

    private final BookmarkService bookmarkService;
    private final UserService userService;

    public FavoriteController(BookmarkService bookmarkService, UserService userService) {
        this.bookmarkService = bookmarkService;
        this.userService = userService;
    }


    @GetMapping
    public String showFavorites(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userService.findUserByUsername(userDetails.getUsername());


        List<Course> courses = bookmarkService.getBookmarksForUser(user).stream()
                .map(Bookmark::getCourse)
                .toList();

        model.addAttribute("courses", courses);
        return "favoriteCourses";
    }


    @PostMapping("/remove/{courseId}")
    public String removeFavorite(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long courseId) {
        User user = userService.findUserByUsername(userDetails.getUsername());
        bookmarkService.removeBookmark(user, courseId);
        return "redirect:/favorites";
    }

    @PostMapping("/add")
    public String addFavorite(@AuthenticationPrincipal UserDetails userDetails,
                              @RequestParam Long courseId,
                              RedirectAttributes redirectAttributes) {
        try {
            User user = userService.findUserByUsername(userDetails.getUsername());
            bookmarkService.addBookmark(user, courseId);
            redirectAttributes.addFlashAttribute("message", "Course added to favorites!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error: " + e.getMessage());
        }
        return "redirect:/coursesDetails?courseId=" + courseId;
    }
}
