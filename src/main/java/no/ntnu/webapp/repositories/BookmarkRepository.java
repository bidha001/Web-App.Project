package no.ntnu.webapp.repositories;

import no.ntnu.webapp.models.Bookmark;
import no.ntnu.webapp.models.Course;
import no.ntnu.webapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    Optional<Bookmark> findByUserAndCourse_CourseId(User user, Long courseId);

    // BookmarkRepository.java
    boolean existsByUserAndCourse(User user, Course course);
}