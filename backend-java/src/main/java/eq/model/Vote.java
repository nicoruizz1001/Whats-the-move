package eq.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "votes")
public class Vote {

    @Id
    private String id;

    @Column(nullable = false)
    private String roomId;

    @Column(nullable = false)
    private String optionId;

    @Column(nullable = false)
    private int value;

    @Column(nullable = false)
    private Instant createdAt;

    protected Vote() {
        // Required by JPA
    }
}
