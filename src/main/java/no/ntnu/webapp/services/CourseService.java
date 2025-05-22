package no.ntnu.webapp.services;

import lombok.Getter;
import no.ntnu.webapp.models.Course;
import no.ntnu.webapp.models.CourseProvider;
import no.ntnu.webapp.models.CourseSession;
import no.ntnu.webapp.models.CourseStatus;
import no.ntnu.webapp.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Service class for handling Course-related business logic
 */
@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }    /**
     * Get all courses (for admin use)
     * @return List of all courses
     */
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
    
    /**
     * Get all active courses (for regular users)
     * @return List of all active courses
     */
    public List<Course> getAllActiveCourses() {
        return courseRepository.findByStatus(CourseStatus.ACTIVE);
    }

    /**
     * Get courses by category name (for admin use)
     * @param categoryName Name of the category
     * @return List of courses in the specified category
     */
    public List<Course> getCoursesByCategory(String categoryName) {
        return courseRepository.findByCategory_Name(categoryName);
    }
    
    /**
     * Get active courses by category name (for regular users)
     * @param categoryName Name of the category
     * @return List of active courses in the specified category
     */
    public List<Course> getActiveCoursesByCategory(String categoryName) {
        return courseRepository.findByCategory_NameAndStatus(categoryName, CourseStatus.ACTIVE);
    }

    /**
     * Get course by ID
     * @param courseId ID of the course
     * @return Optional containing the course if found
     */
    public Optional<Course> getCourseById(Long courseId) {
        return courseRepository.findById(courseId);
    }

    /**
     * Get course details including safe lists of providers and sessions
     * @param courseId ID of the course
     * @return CourseDetails object containing course, providers, and next session
     */
    public CourseDetails getCourseDetails(Long courseId) {
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if (courseOptional.isEmpty()) {
            return null;
        }

        Course course = courseOptional.get();
        List<CourseProvider> providers = course.getProviders() == null 
                ? new ArrayList<>() 
                : new ArrayList<>(course.getProviders());

        List<CourseSession> sessions = course.getSessions() == null 
                ? new ArrayList<>() 
                : new ArrayList<>(course.getSessions());

        CourseSession nextSession = sessions.stream()
                .filter(s -> s.getStartDate().isAfter(LocalDate.now()))
                .min(Comparator.comparing(CourseSession::getStartDate))
                .orElse(null);

        return new CourseDetails(course, providers, nextSession);
    }

    /**
     * Save or update a course
     * @param course Course to save or update
     * @return Saved course
     */
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    /**
     * Delete a course by ID
     * @param courseId ID of the course to delete
     */
    public void deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
    }    /**
     * Search all courses (for admin use)
     * @param query Search query
     * @return List of matching courses
     */
    public List<Course> searchCourses(String query) {
        return courseRepository.findByTitleContainingIgnoreCase(query);
    }
    
    /**
     * Search only active courses (for regular users)
     * @param query Search query
     * @return List of matching active courses
     */
    public List<Course> searchActiveCourses(String query) {
        return courseRepository.findByTitleContainingIgnoreCaseAndStatus(query, CourseStatus.ACTIVE);
    }

    /**
     * DTO class for course details
     */
    @Getter
    public static class CourseDetails {
        private final Course course;
        private final List<CourseProvider> providers;
        private final CourseSession nextSession;

        public CourseDetails(Course course, List<CourseProvider> providers, CourseSession nextSession) {
            this.course = course;
            this.providers = providers;
            this.nextSession = nextSession;
        }
    }
}