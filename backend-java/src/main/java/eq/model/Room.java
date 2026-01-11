package eq.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

import java.time.Instant;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    private String id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private double anchorLat;

    @Column(nullable = false)
    private double anchorLon;

    @Column(nullable = false)
    private int defaultRadiusMiles;

    @Column(nullable = false)
    private Instant createdAt;

    protected Room() {

    }
}
