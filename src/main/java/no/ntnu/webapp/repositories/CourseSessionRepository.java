package no.ntnu.webapp.repositories;

import no.ntnu.webapp.models.Course;
import no.ntnu.webapp.models.CourseSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CourseSessionRepository extends JpaRepository<CourseSession, Long> {
    
    /**
     * Find all course sessions for a specific course
     */
    List<CourseSession> findByCourse(Course course);
    
    /**
     * Find all active course sessions for a specific course
     */
    List<CourseSession> findByCourseAndIsActiveTrue(Course course);
    
    /**
     * Find all course sessions that start after a specific date
     */
    List<CourseSession> findByStartDateAfter(LocalDate date);
    
    /**
     * Find the next upcoming session for a course
     */
    CourseSession findFirstByCourseAndStartDateAfterOrderByStartDate(Course course, LocalDate date);
}
