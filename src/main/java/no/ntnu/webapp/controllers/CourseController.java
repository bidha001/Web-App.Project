package no.ntnu.webapp.controllers;
import no.ntnu.webapp.models.Course;
import no.ntnu.webapp.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/informationTechnologies")
    public String getInformationTechnologiesPage(Model model) {
        List<Course> courses = courseService.getCoursesByCategory("Information Technologies");
        model.addAttribute("courses", courses);
        return "informationTechnologies";
    }

    @GetMapping("/digitalMarketing")
    public String getDigitalMarketingPage(Model model) {
        List<Course> courses = courseService.getCoursesByCategory("Digital Marketing");
        model.addAttribute("courses", courses);
        return "digitalMarketing";
    }

    @GetMapping("/dataScienceAnalytics")
    public String getDataScienceAnalyticsPage(Model model) {
        List<Course> courses = courseService.getCoursesByCategory("Data Science and Analytics");
        model.addAttribute("courses", courses);
        return "dataScienceAnalytics";
    }

    @GetMapping("/businessEntrepreneurship")
    public String getBusinessEntrepreneurshipPage(Model model) {
        List<Course> courses = courseService.getCoursesByCategory("Business and Entrepreneurship");
        model.addAttribute("courses", courses);
        return "businessEntrepreneurship";
    }

    @GetMapping("/course")
    public String getCoursePage(Model model) {
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "course";
    }

    @GetMapping("/coursesDetails")
    public String getCourseDetailsPage(@RequestParam("courseId") Long courseId, Model model) {
        CourseService.CourseDetails courseDetails = courseService.getCourseDetails(courseId);
        if (courseDetails == null) {
            return "redirect:/course"; // or error page
        }

        model.addAttribute("course", courseDetails.getCourse());
        model.addAttribute("providers", courseDetails.getProviders());
        model.addAttribute("nextSession", courseDetails.getNextSession());

        return "coursesDetails";
    }
}
