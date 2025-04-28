package no.ntnu.webapp.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Entity
@Table(name = "categories")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;
    
    @Column(nullable = false)
    private String name;
    
    private String description;
    
    @OneToMany(mappedBy = "category")
    private Set<Course> courses;
}