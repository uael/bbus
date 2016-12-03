package unice.s3a.bus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Bus repository.
 */
@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
    Bus findOneByName(String name);
}
