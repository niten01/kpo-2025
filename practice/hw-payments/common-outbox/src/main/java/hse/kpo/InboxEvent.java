package hse.kpo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "inbox_events")
@Data
@NoArgsConstructor
public class InboxEvent {
    @Id
    private UUID eventId;
    @Column(columnDefinition = "text")
    private String payload;
    private Instant recievedAt;
    private Instant processedAt;

}
