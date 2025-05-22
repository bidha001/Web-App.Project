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

/**
 * CartController.java
 * This class handles requests related to the shopping cart functionality.
 */
@Controller
public class CartController {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final CourseRepository courseRepository;
    private final CourseProviderRepository providerRepository;

    /**
     * Constructor for CartController.
     *
     * @param userRepository      Repository for user data
     * @param orderRepository     Repository for order data
     * @param courseRepository    Repository for course data
     * @param providerRepository  Repository for course provider data
     */
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

    /**
     * Adds a course to the user's cart.
     *
     * @param courseId           The ID of the course to add
     * @param providerId         The ID of the course provider
     * @param principal          The authenticated user
     * @param redirectAttributes Redirect attributes for flash messages
     * @return Redirects to the cart page
     */
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

    /**
     * Displays the user's cart.
     *
     * @param model     The model to add attributes to
     * @param principal The authenticated user
     * @return The name of the view to be rendered
     */
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

    /**
     * Removes a course from the user's cart.
     *
     * @param orderId           The ID of the order to remove
     * @param principal         The authenticated user
     * @param redirectAttributes Redirect attributes for flash messages
     * @return Redirects to the cart page
     */
    @GetMapping("/removeFromCart")
    public String removeFromCart(@RequestParam Long orderId, Principal principal, RedirectAttributes redirectAttributes) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null && order.getUser().getUsername().equals(principal.getName())) {
            orderRepository.delete(order);
            redirectAttributes.addFlashAttribute("success", "Course removed from cart.");
        } else {
            redirectAttributes.addFlashAttribute("error", "Could not remove course.");
        }
        return "redirect:/cart";
    }

    /**
     * Displays the payment page.
     *
     * @param model     The model to add attributes to
     * @param principal The authenticated user
     * @return The name of the view to be rendered
     */
    @GetMapping("/checkout")
    public String showPaymentPage(Model model, Principal principal, RedirectAttributes redirectAttributes) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow();
        List<Order> orders = orderRepository.findByUser_UserIdAndOrderStatus(user.getUserId(), OrderStatus.PENDING);

        if (orders.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Your cart is empty. Add courses before checking out.");
            return "redirect:/cart";
        }

        double total = 0;
        for (Order o : orders) {
            CourseProvider provider = o.getCourse().getProviders().stream().findFirst().orElse(null);
            if (provider != null) {
                total += provider.getPrice().doubleValue();
            }
        }

        model.addAttribute("orders", orders);
        model.addAttribute("total", total);
        return "payment";
    }

    /**
     * Processes the payment for the user's cart.
     *
     * @param cardName           The name on the card
     * @param cardNumber         The card number
     * @param expiry             The card expiry date
     * @param cvc                The card CVC
     * @param principal          The authenticated user
     * @param model Redirect attributes for flash messages
     * @return Redirects to the home page or a confirmation page
     */
    @PostMapping("/processPayment")
    public String processPayment(@RequestParam String cardName,
                                 @RequestParam String cardNumber,
                                 @RequestParam String expiry,
                                 @RequestParam String cvc,
                                 Principal principal,
                                 Model model,
                                 RedirectAttributes redirectAttributes) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow();
        List<Order> orders = orderRepository.findByUser_UserIdAndOrderStatus(user.getUserId(), OrderStatus.PENDING);

        if (orders.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Cannot process payment. Your cart is empty.");
            return "redirect:/cart";
        }

        for (Order order : orders) {
            order.setOrderStatus(OrderStatus.COMPLETED);
            order.setFakeCardLast4(cardNumber.length() >= 4
                    ? cardNumber.substring(cardNumber.length() - 4)
                    : "0000");
            orderRepository.save(order);
        }

        model.addAttribute("showSuccessPopup", true);
        return "payment";
    }


    /**
     * Displays the user's completed courses.
     *
     * @param model     The model to add attributes to
     * @param principal The authenticated user
     * @return The name of the view to be rendered
     */
    @GetMapping("/my-courses")
    public String showMyCourses(Model model, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow();
        List<Order> completedOrders = orderRepository.findByUser_UserIdAndOrderStatus(user.getUserId(), OrderStatus.COMPLETED);
        model.addAttribute("completedOrders", completedOrders);
        return "MyCourses";
    }

}
