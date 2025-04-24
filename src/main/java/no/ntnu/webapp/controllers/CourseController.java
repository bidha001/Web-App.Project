package no.ntnu.webapp.controllers;

@Controller
public class CourseController {
  @GetMapping("/home")
    public String home() {
        return "home"; // no .html extension needed
    }
}
