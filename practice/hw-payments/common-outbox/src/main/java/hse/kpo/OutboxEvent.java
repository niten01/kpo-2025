package hse.kpo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "outbox_events")
@Data
@NoArgsConstructor
public class OutboxEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String aggregateType;
    private String aggregateId;
    private String eventType;
    @Column(columnDefinition = "text")
    private String payload;
    private Instant createdAt;
    private Instant processedAt;
}
