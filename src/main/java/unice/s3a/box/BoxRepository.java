package unice.s3a.box;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Box repository.
 */
@Repository
public interface BoxRepository extends JpaRepository<Box, String> { }
