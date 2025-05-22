package no.ntnu.webapp.services;

import jakarta.transaction.Transactional;
import no.ntnu.webapp.models.Bookmark;
import no.ntnu.webapp.models.Course;
import no.ntnu.webapp.models.User;
import no.ntnu.webapp.repositories.BookmarkRepository;
import no.ntnu.webapp.repositories.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    private final UserService userService;
    private final CourseRepository courseRepository;

    public BookmarkService(BookmarkRepository bookmarkRepository, UserService userService, CourseRepository courseRepository) {
        this.bookmarkRepository = bookmarkRepository;
        this.userService = userService;
        this.courseRepository = courseRepository;
    }

    public void removeBookmark(User user, Long courseId) {
        Optional<Bookmark> bookmark = bookmarkRepository.findByUserAndCourse_CourseId(user, courseId);
        bookmark.ifPresent(bookmarkRepository::delete);
    }

    public void addBookmark(User user, Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));

        if (!bookmarkRepository.existsByUserAndCourse(user, course)) {
            Bookmark bookmark = new Bookmark();
            bookmark.setUser(user);
            bookmark.setCourse(course);
            bookmarkRepository.save(bookmark);
        }
    }

    public List<Bookmark> getBookmarksForUser(User user) {
        return bookmarkRepository.findAllByUser(user);
    }
}
