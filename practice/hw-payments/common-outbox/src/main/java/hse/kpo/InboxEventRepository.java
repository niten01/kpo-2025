package hse.kpo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface InboxEventRepository extends JpaRepository<InboxEvent, UUID> {
    List<InboxEvent> findByProcessedAtIsNull();
}
