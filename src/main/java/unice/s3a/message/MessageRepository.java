package unice.s3a.message;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Message repository.
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> { }
