package no.ntnu.webapp.repositories;

import no.ntnu.webapp.models.CourseSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CourseSessionRepository extends JpaRepository<CourseSession, Long> {
    /**
     * Find sessions by course ID
     * @param courseId ID of the course
     * @return List of sessions for the specified course
     */
    List<CourseSession> findByCourse_CourseId(Long courseId);
    
    /**
     * Find upcoming sessions by course ID
     * @param courseId ID of the course
     * @param currentDate Current date
     * @return List of upcoming sessions for the specified course
     */
    List<CourseSession> findByCourse_CourseIdAndStartDateAfter(Long courseId, LocalDate currentDate);
    
    /**
     * Find sessions by user ID
     * @param userId ID of the user
     * @return List of sessions for the specified user
     */
    List<CourseSession> findByUser_UserId(Long userId);
}