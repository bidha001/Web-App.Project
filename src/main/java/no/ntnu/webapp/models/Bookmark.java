package no.ntnu.webapp.models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;


@Entity
@Table(name = "bookmarks")
@Data

public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookmarkId;

    /**
     * The user who created the bookmark.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * The course that is bookmarked.
     */
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    /**
     * The timestamp when the bookmark was created.
     */
    @Column(nullable = false)
    private LocalDateTime createdAt;

    /**
     * The timestamp when the bookmark was last updated.
     */
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}