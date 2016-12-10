package unice.s3a.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unice.s3a.model.Box;

/**
 * The interface Box repository.
 */
@Repository
public interface BoxRepository extends JpaRepository<Box, Long> {
    Box findOneByName(String name);
}
