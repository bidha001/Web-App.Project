package no.ntnu.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class CourseController {

    @GetMapping("/informationTechnologies")
    public String informationTechnologies(Model model) {
        model.addAttribute("courses", getCourses());
        return "informationTechnologies";
    }

    @GetMapping("/digitalMarketing")
    public String digitalMarketing(Model model) {
        model.addAttribute("courses", getCourses());
        return "digitalMarketing";
    }

    @GetMapping("/dataScienceAnalytics")
    public String dataScienceAnalytics(Model model) {
        model.addAttribute("courses", getCourses());
        return "dataScienceAnalytics";
    }

    @GetMapping("/businessEntrepreneurship")
    public String businessEntrepreneurship(Model model) {
        model.addAttribute("courses", getCourses());
        return "businessEntrepreneurship";
    }

    @GetMapping("/course")
    public String course (Model model) {
        model.addAttribute("courses", getCourses());
        return "course";
    }

    private List<Course> getCourses() {
        return List.of(
                new Course(1, "Java.svg", "Real-Time Programming in Java"),
                new Course(2, "SQL.jpg", "SQL"),
                new Course(3, "Net web.jpg", "Web Design"),
                new Course(4, "Azure Fundamentals.png", "Azure Fundamentals"),
                new Course(5, "Amazon Web Services.png", "Amazon Web Services"),
                new Course(6, "AWS Cloud Practitioner.png", "AWS Cloud Practitioner"),
                new Course(7, "Search Engine Optimization.png", "SEO"),
                new Course(8, "social-media-marketing.jpg", "Social Media Marketing"),
                new Course(9, "Business Strategy.png", "Business Strategy"),
                new Course(10, "Machine Learning.jpg", "Machine Learning Basics"),
                new Course(11, "Image Recognition.jpg", "Image Recognition"),
                new Course(12, "Databricks.png", "Databricks Fundamentals")
        );
    }

    public record Course(int id, String image, String title) {}
}
