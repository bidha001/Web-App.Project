package no.ntnu.webapp.controllers;
import no.ntnu.webapp.models.Course;
import no.ntnu.webapp.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

/**
 * CourseController.java
 * This class handles requests related to courses and their categories.
 */
@Controller
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    /**
     * Handles requests to the course categories and their respective pages.
     * @param model Model object to pass data to the view
     * @return The name of the view to be rendered
     */
    @GetMapping("/informationTechnologies")
    public String getInformationTechnologiesPage(Model model) {
        List<Course> courses = courseService.getCoursesByCategory("Information Technologies");
        model.addAttribute("courses", courses);
        model.addAttribute("categoryTitle", "Information Technologies");
        return "informationTechnologies";
    }

    /**
     * Handles requests to the Digital Marketing category page.
     * @param model Model object to pass data to the view
     * @return The name of the view to be rendered
     */
    @GetMapping("/digitalMarketing")
    public String getDigitalMarketingPage(Model model) {
        List<Course> courses = courseService.getCoursesByCategory("Digital Marketing");
        model.addAttribute("courses", courses);
        model.addAttribute("categoryTitle", "Digital Marketing");
        return "digitalMarketing";
    }

    /**
     * Handles requests to the Business and Entrepreneurship category page.
     * @param model Model object to pass data to the view
     * @return The name of the view to be rendered
     */
    @GetMapping("/businessEntrepreneurship")
    public String getBusinessEntrepreneurshipPage(Model model) {
        List<Course> courses = courseService.getCoursesByCategory("Business and Entrepreneurship");
        model.addAttribute("courses", courses);
        model.addAttribute("categoryTitle", "Business & Entrepreneurship");
        return "businessEntrepreneurship";
    }

    /**
     * Handles requests to the Data Science and Analytics category page.
     * @param model Model object to pass data to the view
     * @return The name of the view to be rendered
     */
    @GetMapping("/dataScienceAnalytics")
    public String getDataScienceAnalyticsPage(Model model) {
        List<Course> courses = courseService.getCoursesByCategory("Data Science and Analytics");
        model.addAttribute("courses", courses);
        model.addAttribute("categoryTitle", "Data Science and Analytics");
        return "dataScienceAnalytics";
    }

    /**
     * Handles requests to the course page, displaying all courses.
     * @param model Model object to pass data to the view
     * @return The name of the view to be rendered
     */
    @GetMapping("/course")
    public String getCoursePage(Model model) {
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        model.addAttribute("categoryTitle", "All Courses");
        return "course";
    }

    /**
     * Handles requests to the course details page.
     * @param courseId ID of the course to display
     * @param model Model object to pass data to the view
     * @return The name of the view to be rendered
     */
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
