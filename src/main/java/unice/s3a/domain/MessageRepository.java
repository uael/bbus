package unice.s3a.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unice.s3a.model.Message;

/**
 * The interface Message repository.
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> { }
