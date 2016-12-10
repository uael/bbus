package unice.s3a.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unice.s3a.model.Bus;

/**
 * The interface Bus repository.
 */
@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
    Bus findOneByName(String name);
}
