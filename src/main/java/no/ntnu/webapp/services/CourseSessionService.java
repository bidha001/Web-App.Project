package no.ntnu.webapp.services;

import no.ntnu.webapp.models.Course;
import no.ntnu.webapp.models.CourseSession;
import no.ntnu.webapp.repositories.CourseSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CourseSessionService {
    
    private final CourseSessionRepository courseSessionRepository;
    
    @Autowired
    public CourseSessionService(CourseSessionRepository courseSessionRepository) {
        this.courseSessionRepository = courseSessionRepository;
    }
    
    /**
     * Get all course sessions
     */
    public List<CourseSession> getAllCourseSessions() {
        return courseSessionRepository.findAll();
    }
    
    /**
     * Get a course session by ID
     */
    public Optional<CourseSession> getCourseSessionById(Long id) {
        return courseSessionRepository.findById(id);
    }
    
    /**
     * Get all sessions for a specific course
     */
    public List<CourseSession> getSessionsForCourse(Course course) {
        return courseSessionRepository.findByCourse(course);
    }
    
    /**
     * Get all active sessions for a specific course
     */
    public List<CourseSession> getActiveSessionsForCourse(Course course) {
        return courseSessionRepository.findByCourseAndIsActiveTrue(course);
    }
    
    /**
     * Get the next upcoming session for a course
     */
    public CourseSession getNextSessionForCourse(Course course) {
        return courseSessionRepository.findFirstByCourseAndStartDateAfterOrderByStartDate(course, LocalDate.now());
    }
    
    /**
     * Create a new course session
     */
    public CourseSession createCourseSession(CourseSession courseSession) {
        return courseSessionRepository.save(courseSession);
    }
    
    /**
     * Update an existing course session
     */
    public CourseSession updateCourseSession(CourseSession courseSession) {
        return courseSessionRepository.save(courseSession);
    }
    
    /**
     * Delete a course session
     */
    public void deleteCourseSession(Long id) {
        courseSessionRepository.deleteById(id);
    }
}
