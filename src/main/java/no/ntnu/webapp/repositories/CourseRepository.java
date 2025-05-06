package no.ntnu.webapp.repositories;
import no.ntnu.webapp.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Repository interface for Course entity.
 * This interface extends JpaRepository to provide CRUD operations for Course entities.
 */
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByCategory_Name(String name);
    List<Course> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String title, String description);
    
    /**
     * Find courses by title (case-insensitive)
     * @param title the title to search for
     * @return list of courses with matching titles
     */
    List<Course> findByTitleContainingIgnoreCase(String title);
}
