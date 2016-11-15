package unice.s3a.message;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unice.s3a.bus.Bus;

@Repository
public interface MessageRepository extends JpaRepository<Message, String> { }
