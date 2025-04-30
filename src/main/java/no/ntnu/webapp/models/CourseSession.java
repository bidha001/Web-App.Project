package no.ntnu.webapp.models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "course_sessions")
@Data
public class CourseSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sessionId;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    @JsonIgnore
    private Course course;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // For tracking individual student participation in a session
    private LocalDateTime enrollmentDate;

    /**
     * Returns a formatted string representation of the session period
     * Example: "June 3rd – June 28th"
     */
    @Transient // This field is not persisted in the database
    public String getFormattedPeriod() {
        if (startDate == null || endDate == null) {
            return "";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d");
        String start = startDate.format(formatter);
        String end = endDate.format(formatter);

        // Add ordinal suffix (st, nd, rd, th)
        start = addOrdinalSuffix(start);
        end = addOrdinalSuffix(end);

        return start + " – " + end;
    }

    /**
     * Helper method to add ordinal suffix to a date
     * Example: "June 3" becomes "June 3rd"
     */
    private String addOrdinalSuffix(String date) {
        String[] parts = date.split(" ");
        if (parts.length != 2) {
            return date;
        }

        String month = parts[0];
        int day;
        try {
            day = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            return date;
        }

        String suffix;
        if (day >= 11 && day <= 13) {
            suffix = "th";
        } else {
            switch (day % 10) {
                case 1:
                    suffix = "st";
                    break;
                case 2:
                    suffix = "nd";
                    break;
                case 3:
                    suffix = "rd";
                    break;
                default:
                    suffix = "th";
                    break;
            }
        }

        return month + " " + day + suffix;
    }
}