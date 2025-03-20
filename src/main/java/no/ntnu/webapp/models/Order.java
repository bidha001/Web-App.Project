package no.ntnu.webapp.models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
    
    @Column(nullable = false)
    private LocalDateTime orderTimestamp;
    
    private String fakeCardLast4;
    
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    
    @PrePersist
    protected void onCreate() {
        orderTimestamp = LocalDateTime.now();
    }
}