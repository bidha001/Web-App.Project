package no.ntnu.webapp.repositories;
import no.ntnu.webapp.models.Course;
import no.ntnu.webapp.models.CourseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Repository interface for Course entity.
 * This interface extends JpaRepository to provide CRUD operations for Course entities.
 */
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByCategory_Name(String name);
    List<Course> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String title, String description);
    List<Course> findByTitleContainingIgnoreCase(String query);
    
    // New methods to filter by status
    List<Course> findByStatus(CourseStatus status);
    List<Course> findByCategory_NameAndStatus(String name, CourseStatus status);
    List<Course> findByTitleContainingIgnoreCaseAndStatus(String query, CourseStatus status);
}
