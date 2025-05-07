package no.ntnu.webapp.models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    
    @Column(nullable = false, unique = true)
    private String username;
    
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;
    
    private String profileImageUrl;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    @OneToMany(mappedBy = "user")
    private Set<Order> orders;
    
    @OneToMany(mappedBy = "user")
    private Set<Rating> ratings;
    
    @OneToMany(mappedBy = "user")
    private Set<CourseSession> courseSessions;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Bookmark> bookmarks;

    @Transient
    private String password;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public Set<Bookmark> getBookmarks() {
        return this.bookmarks;
    }

}