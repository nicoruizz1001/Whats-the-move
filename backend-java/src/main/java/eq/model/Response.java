package eq.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

import java.time.Instant;

@Entity
@Table(name = "responses")
public class Response {

    @Id
    private String id;

    @Column(nullable = false)
    private String roomId;

    @Column(nullable = false)
    private String activityType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Budget budget;


    @Column
    private Integer radiusMiles;

    @Column
    private String notes;

    @Column(nullable = false)
    private Instant createdAt;

    protected Response() {
        // Required by JPA
    }
}
