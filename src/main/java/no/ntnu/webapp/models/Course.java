package no.ntnu.webapp.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Set;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "courses")
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;
    
    @Column(nullable = false)
    private String title;
    
    @Column(length = 1000)
    private String description;
    
    @Enumerated(EnumType.STRING)
    private CourseLevel level;

    private Double ectsCredits;
    private Integer hoursPerWeek;
    private String courseImageUrl;
    private String certification;
    
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    
    @OneToMany(mappedBy = "course")
    private Set<CourseProvider> providers;
    
    @OneToMany(mappedBy = "course")
    private Set<Rating> ratings;
    
    @OneToMany(mappedBy = "course")
    private Set<CourseSession> sessions;
    
    @OneToMany(mappedBy = "course")
    private Set<Bookmark> bookmarks;

}
