package no.ntnu.webapp.repositories;
import no.ntnu.webapp.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByCategory_CategoryName(String categoryName);
}
