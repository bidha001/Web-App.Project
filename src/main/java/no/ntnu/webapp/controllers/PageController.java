package no.ntnu.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;


/**
 * PageController.java
 * This class handles requests for various pages in the web application.
 */
@Controller
public class PageController {

    /**
     * Handles requests to the root URL (/) and redirects to home page.
     * @return RedirectView to home page
     */
    @GetMapping("/")
    public RedirectView redirectToHome() {
        return new RedirectView("/home");
    }

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



    //@GetMapping("/cart")
    //**public String cart() {
       // return "cart";
    //}



}
