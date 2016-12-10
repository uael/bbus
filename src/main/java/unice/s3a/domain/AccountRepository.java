package unice.s3a.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unice.s3a.model.Account;

/**
 * The interface Account repository.
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    /**
     * Find one by email account.
     * @param email the email
     * @return the account
     */
    Account findOneByEmail(String email);
}