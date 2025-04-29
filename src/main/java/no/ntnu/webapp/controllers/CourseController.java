package no.ntnu.webapp.controllers;
import jakarta.transaction.Transactional;
import no.ntnu.webapp.models.Course;
import no.ntnu.webapp.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class CourseController {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GetMapping("/informationTechnologies")
    public String getInformationTechnologiesPage(Model model) {
        List<Course> courses = courseRepository.findByCategory_Name("Information Technologies");
        model.addAttribute("courses", courses);
        return "informationTechnologies";
    }

    @GetMapping("/digitalMarketing")
    public String getDigitalMarketingPage(Model model) {
        List<Course> courses = courseRepository.findByCategory_Name("Digital Marketing");
        model.addAttribute("courses", courses);
        return "digitalMarketing";
    }

    @GetMapping("/dataScienceAnalytics")
    public String getDataScienceAnalyticsPage(Model model) {
        List<Course> courses = courseRepository.findByCategory_Name("Data Science and Analytics");
        model.addAttribute("courses", courses);
        return "dataScienceAnalytics";
    }

    @GetMapping("/businessEntrepreneurship")
    public String getBusinessEntrepreneurshipPage(Model model) {
        List<Course> courses = courseRepository.findByCategory_Name("Business and Entrepreneurship");
        model.addAttribute("courses", courses);
        return "businessEntrepreneurship";
    }

    @GetMapping("/course")
    @Transactional
    public String getCoursePage(Model model) {
        List<Course> courses = courseRepository.findAll();
        model.addAttribute("courses", courses);
        return "course";
    }
}
