package no.ntnu.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * PageController.java
 * This class handles requests for various pages in the web application.
 */
@Controller
public class PageController {

    /**
     * Handles requests to the home page.
     * @return The name of the view to be rendered
     */
    @GetMapping("/home")
    public String home() {
        return "home";
    }

//    @GetMapping("/login")
//    public String loginPage() {
//        return "login";
//    }
//
//    @GetMapping("/signup")
//    public String signupPage() {
//        return "signup";
//    }

    /**
     * Handles requests to the information technologies page.
     * @return The name of the view to be rendered
     */
    @GetMapping("/contactUs")
    public String contactUs() {
        return "contactUs";
    }


    /**
     * Handles requests to the cart page.
     * @return The name of the view to be rendered
     */
    @GetMapping("/cart")
    public String cart() {
        return "cart";
    }

}
