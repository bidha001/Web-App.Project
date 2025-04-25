package no.ntnu.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class CourseController {

    @GetMapping("/informationTechnologies")
    public String courses(Model model) {
        List<Course> courseList = List.of(
                new Course(1, "Java.svg", "Real-Time Programming in Java"),
                new Course(2, "SQL.jpg", "SQL"),
                new Course(3, "Net web.jpg", "WEB DESIGN"),
                new Course(4, "Azure Fundamentals.png", "Azure Fundamentals"),
                new Course(5, "Amazon Web Services.png", "Amazon Web Services"),
                new Course(6, "AWS Cloud Practitioner.png", "AWS Cloud Practitioner"),
                new Course(7, "Search Engine Optimization.png", "Search Engine Optimization"),
                new Course(8, "social-media-marketing.jpg", "Social Media Marketing"),
                new Course(9, "Business Strategy.png", "Business Strategy"),
                new Course(10, "Machine Learning.jpg", "Machine Learning Basics with Python"),
                new Course(11, "Image Recognition.jpg", "Image Recognition"),
                new Course(12, "Databricks.png", "Databricks fundamentals")
        );

        model.addAttribute("courses", courseList);
        return "informationTechnologies";
    }

    public record Course(int id, String image, String title) {}
}
