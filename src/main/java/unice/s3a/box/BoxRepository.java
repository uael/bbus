package unice.s3a.box;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unice.s3a.account.Account;

/**
 * The interface Box repository.
 */
@Repository
public interface BoxRepository extends JpaRepository<Box, Long> {
    Account findOneByName(String email);
}
