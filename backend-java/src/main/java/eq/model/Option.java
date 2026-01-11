package eq.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "options")
public class Option {

    @Id
    private String id;

    @Column(nullable = false)
    private String roomId;

    @Column(nullable = false)
    private String name;

    @Column
    private String address;

    @Column(nullable = false)
    private double lat;

    @Column(nullable = false)
    private double lon;

    @Column
    private String reason;

    @Column(nullable = false)
    private Instant createdAt;

    protected Option() {

    }
}
