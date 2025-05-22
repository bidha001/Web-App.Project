package no.ntnu.webapp.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Represents a course provider.
 */
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "course_providers")
@Data

public class CourseProvider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long providerId;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonIgnore
    private Course course;

    @Column(nullable = false)
    private String providerName;

    @Column(nullable = false)
    private BigDecimal price;
    
    @Column(nullable = false)
    private String currency;
}