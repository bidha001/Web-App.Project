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
}
