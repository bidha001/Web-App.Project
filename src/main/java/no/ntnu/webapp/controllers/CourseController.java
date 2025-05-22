package no.ntnu.webapp.controllers;
import no.ntnu.webapp.models.Category;
import no.ntnu.webapp.models.Course;
import no.ntnu.webapp.models.CourseProvider;
import no.ntnu.webapp.models.CourseStatus;
import no.ntnu.webapp.services.CategoryService;
import no.ntnu.webapp.services.CourseProviderService;
import no.ntnu.webapp.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * CourseController.java
 * This class handles requests related to courses and their categories.
 */
@Controller
public class CourseController {
    private final CourseService courseService;
    private final CategoryService categoryService;
    private final CourseProviderService courseProviderService;

    @Autowired
    public CourseController(CourseService courseService, CategoryService categoryService, 
                           CourseProviderService courseProviderService) {
        this.courseService = courseService;
        this.categoryService = categoryService;
        this.courseProviderService = courseProviderService;
    }

    /**
     * Handles requests to the course categories and their respective pages.
     * @param model Model object to pass data to the view
     * @return The name of the view to be rendered
     */    @GetMapping("/informationTechnologies")
    public String getInformationTechnologiesPage(Model model) {
        List<Course> courses = courseService.getActiveCoursesByCategory("Information Technologies");
        model.addAttribute("courses", courses);
        model.addAttribute("categoryTitle", "Information Technologies");
        return "informationTechnologies";
    }

    /**
     * Handles requests to the Digital Marketing category page.
     * @param model Model object to pass data to the view
     * @return The name of the view to be rendered
     */    @GetMapping("/digitalMarketing")
    public String getDigitalMarketingPage(Model model) {
        List<Course> courses = courseService.getActiveCoursesByCategory("Digital Marketing");
        model.addAttribute("courses", courses);
        model.addAttribute("categoryTitle", "Digital Marketing");
        return "digitalMarketing";
    }

    /**
     * Handles requests to the Business and Entrepreneurship category page.
     * @param model Model object to pass data to the view
     * @return The name of the view to be rendered
     */    @GetMapping("/businessEntrepreneurship")
    public String getBusinessEntrepreneurshipPage(Model model) {
        List<Course> courses = courseService.getActiveCoursesByCategory("Business and Entrepreneurship");
        model.addAttribute("courses", courses);
        model.addAttribute("categoryTitle", "Business & Entrepreneurship");
        return "businessEntrepreneurship";
    }

    /**
     * Handles requests to the Data Science and Analytics category page.
     * @param model Model object to pass data to the view
     * @return The name of the view to be rendered
     */    @GetMapping("/dataScienceAnalytics")
    public String getDataScienceAnalyticsPage(Model model) {
        List<Course> courses = courseService.getActiveCoursesByCategory("Data Science and Analytics");
        model.addAttribute("courses", courses);
        model.addAttribute("categoryTitle", "Data Science and Analytics");
        return "dataScienceAnalytics";
    }

    /**
     * Handles requests to the course page, displaying all courses.
     * @param model Model object to pass data to the view
     * @return The name of the view to be rendered
     */    @GetMapping("/course")
    public String getCoursePage(Model model) {
        List<Course> courses = courseService.getAllActiveCourses();
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

    /**
     * Handles requests to the search page.
     * @param query Search query for course title
     * @param model Model object to pass data to the view
     * @return The name of the view to be rendered
     */    @GetMapping("/search")
    public String searchCourses(@RequestParam("query") String query, Model model) {
        List<Course> matchedCourses = courseService.searchActiveCourses(query);
        model.addAttribute("courses", matchedCourses);
        model.addAttribute("categoryTitle", "Search Results for \"" + query + "\"");
        model.addAttribute("searchQuery", query);
        return "searchResults";
    }/**
     * Handles requests to the admin dashboard page.
     * @param model Model object to pass data to the view
     * @return The name of the view to be rendered
     */
    @GetMapping("/admin-dashboard")
    public String getAdminDashboard(Model model) {
        List<Course> courses = courseService.getAllCourses();
        List<Category> categories = categoryService.getAllCategories();
        List<CourseProvider> providers = courseProviderService.getAllProviders();
        model.addAttribute("courses", courses);
        model.addAttribute("categories", categories);
        model.addAttribute("providers", providers);
        model.addAttribute("newCourse", new Course());
        model.addAttribute("newProvider", new CourseProvider());
        return "admin-dashboard";
    }    /**
     * Handles adding a new course
     * @param course The course object from form submission
     * @param categoryId The ID of the selected category
     * @param providerName The name of the provider
     * @param price The price of the course
     * @param currency The currency used for the price
     * @return Redirects to admin dashboard
     */
    @PostMapping("/admin/add-course")
    public String addCourse(@ModelAttribute Course course, 
                           @RequestParam("categoryId") Long categoryId,
                           @RequestParam("providerName") String providerName,
                           @RequestParam("price") BigDecimal price,
                           @RequestParam("currency") String currency) {
        
        Optional<Category> categoryOptional = categoryService.getCategoryById(categoryId);
        if (categoryOptional.isPresent()) {
            course.setCategory(categoryOptional.get());
            Course savedCourse = courseService.saveCourse(course);
            
            // Create and save course provider
            if (providerName != null && !providerName.isEmpty() && price != null) {
                CourseProvider provider = new CourseProvider();
                provider.setCourse(savedCourse);
                provider.setProviderName(providerName);
                provider.setPrice(price);
                provider.setCurrency(currency != null && !currency.isEmpty() ? currency : "USD");
                courseProviderService.saveProvider(provider);
            }
        }
        return "redirect:/admin-dashboard";
    }
    
    /**
     * Shows the edit course form
     * @param courseId ID of the course to edit
     * @param model Model object to pass data to the view
     * @return The name of the view to be rendered
     */    @GetMapping("/admin/edit-course")
    public String showEditCourseForm(@RequestParam("courseId") Long courseId, Model model) {
        Optional<Course> courseOptional = courseService.getCourseById(courseId);
        if (courseOptional.isPresent()) {
            Course course = courseOptional.get();
            model.addAttribute("course", course);
            model.addAttribute("categories", categoryService.getAllCategories());
            List<CourseProvider> providers = courseProviderService.getProvidersByCourse(courseId);
            model.addAttribute("provider", !providers.isEmpty() ? providers.get(0) : new CourseProvider());
            return "edit-course";
        }
        return "redirect:/admin-dashboard";
    }   
    
    /**
     * Toggles course visibility status (ACTIVE/INACTIVE)
     * @param courseId ID of the course to toggle
     * @return Redirects to admin dashboard
     */
    @GetMapping("/admin/toggle-course-status")
    public String toggleCourseStatus(@RequestParam("courseId") Long courseId) {
        Optional<Course> courseOptional = courseService.getCourseById(courseId);
        if (courseOptional.isPresent()) {
            Course course = courseOptional.get();
            // Toggle the status
            if (course.getStatus() == CourseStatus.ACTIVE) {
                course.setStatus(CourseStatus.INACTIVE);
            } else {
                course.setStatus(CourseStatus.ACTIVE);
            }
            courseService.saveCourse(course);
        }
        return "redirect:/admin-dashboard";
    }

    /**
     * Handles updating an existing course
     * @param courseId ID of the course to update
     * @param course The updated course object from form submission
     * @param categoryId The ID of the selected category
     * @param providerName The name of the provider
     * @param providerId The ID of the provider to update
     * @param price The price of the course
     * @param currency The currency used for the price
     * @return Redirects to admin dashboard
     */
    @PostMapping("/admin/update-course")
    public String updateCourse(@RequestParam("courseId") Long courseId,
                             @ModelAttribute Course course,
                             @RequestParam("categoryId") Long categoryId,
                             @RequestParam("providerName") String providerName,
                             @RequestParam(value = "providerId", required = false) Long providerId,
                             @RequestParam("price") BigDecimal price,
                             @RequestParam("currency") String currency) {
        
        Optional<Category> categoryOptional = categoryService.getCategoryById(categoryId);
        if (categoryOptional.isPresent()) {
            course.setCourseId(courseId); // Ensure ID is set correctly
            course.setCategory(categoryOptional.get());
            Course savedCourse = courseService.saveCourse(course);
            
            // Update or create course provider
            if (providerName != null && !providerName.isEmpty() && price != null) {
                CourseProvider provider;
                if (providerId != null) {
                    provider = courseProviderService.getProviderById(providerId).orElse(new CourseProvider());
                } else {
                    provider = new CourseProvider();
                }
                provider.setCourse(savedCourse);
                provider.setProviderName(providerName);
                provider.setPrice(price);
                provider.setCurrency(currency != null && !currency.isEmpty() ? currency : "USD");
                courseProviderService.saveProvider(provider);
            }
        }
        return "redirect:/admin-dashboard";
    }
}
