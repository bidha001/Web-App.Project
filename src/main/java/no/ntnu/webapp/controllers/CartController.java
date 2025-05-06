package no.ntnu.webapp.controllers;

import no.ntnu.webapp.models.*;
import no.ntnu.webapp.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.*;

@Controller
public class CartController {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final CourseRepository courseRepository;
    private final CourseProviderRepository providerRepository;

    @Autowired
    public CartController(UserRepository userRepository,
                          OrderRepository orderRepository,
                          CourseRepository courseRepository,
                          CourseProviderRepository providerRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.courseRepository = courseRepository;
        this.providerRepository = providerRepository;
    }

    @PostMapping("/addToCart")
    public String addToCart(@RequestParam Long courseId,
                            @RequestParam Long providerId,
                            Principal principal,
                            RedirectAttributes redirectAttributes) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow();
        Course course = courseRepository.findById(courseId).orElseThrow();

        Order order = new Order();
        order.setUser(user);
        order.setCourse(course);
        order.setOrderTimestamp(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.PENDING);
        order.setFakeCardLast4("0000"); // Placeholder

        orderRepository.save(order);

        redirectAttributes.addFlashAttribute("success", "Course added to cart!");
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String showCart(Model model, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow();
        List<Order> orders = orderRepository.findByUser_UserIdAndOrderStatus(user.getUserId(), OrderStatus.PENDING);

        double total = 0;
        Map<Long, Double> ratings = new HashMap<>();

        for (Order o : orders) {
            // Handle empty providers list safely
            CourseProvider provider = o.getCourse().getProviders().stream().findFirst().orElse(null);
            if (provider != null) {
                total += provider.getPrice().doubleValue();
            }

            double avgRating = o.getCourse().getRatings().stream()
                    .mapToInt(Rating::getRatingValue)
                    .average()
                    .orElse(0.0);
            ratings.put(o.getCourse().getCourseId(), avgRating);
        }

        model.addAttribute("orders", orders);
        model.addAttribute("total", total);
        model.addAttribute("ratings", ratings);
        return "cart";
    }
}
