package no.ntnu.webapp.services;

import no.ntnu.webapp.models.CourseSession;
import no.ntnu.webapp.repositories.CourseSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Service class for handling CourseSession-related business logic
 */
@Service
public class CourseSessionService {

    private final CourseSessionRepository courseSessionRepository;

    @Autowired
    public CourseSessionService(CourseSessionRepository courseSessionRepository) {
        this.courseSessionRepository = courseSessionRepository;
    }

    /**
     * Get all course sessions
     * @return List of all course sessions
     */
    public List<CourseSession> getAllSessions() {
        return courseSessionRepository.findAll();
    }

    /**
     * Get course session by ID
     * @param sessionId ID of the session
     * @return Optional containing the session if found
     */
    public Optional<CourseSession> getSessionById(Long sessionId) {
        return courseSessionRepository.findById(sessionId);
    }

    /**
     * Get sessions for a specific course
     * @param courseId ID of the course
     * @return List of sessions for the specified course
     */
    public List<CourseSession> getSessionsByCourse(Long courseId) {
        return courseSessionRepository.findByCourse_CourseId(courseId);
    }

    /**
     * Get upcoming sessions for a specific course
     * @param courseId ID of the course
     * @return List of upcoming sessions for the specified course
     */
    public List<CourseSession> getUpcomingSessionsByCourse(Long courseId) {
        return courseSessionRepository.findByCourse_CourseIdAndStartDateAfter(courseId, LocalDate.now());
    }

    /**
     * Get next upcoming session for a specific course
     * @param courseId ID of the course
     * @return Next upcoming session for the specified course, or null if none found
     */
    public CourseSession getNextSessionForCourse(Long courseId) {
        List<CourseSession> upcomingSessions = getUpcomingSessionsByCourse(courseId);
        return upcomingSessions.stream()
                .min((s1, s2) -> s1.getStartDate().compareTo(s2.getStartDate()))
                .orElse(null);
    }

    /**
     * Get sessions for a specific user
     * @param userId ID of the user
     * @return List of sessions for the specified user
     */
    public List<CourseSession> getSessionsByUser(Long userId) {
        return courseSessionRepository.findByUser_UserId(userId);
    }

    /**
     * Save or update a course session
     * @param session Session to save or update
     * @return Saved session
     */
    public CourseSession saveSession(CourseSession session) {
        return courseSessionRepository.save(session);
    }

    /**
     * Delete a session by ID
     * @param sessionId ID of the session to delete
     */
    public void deleteSession(Long sessionId) {
        courseSessionRepository.deleteById(sessionId);
    }
}