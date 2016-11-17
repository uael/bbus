package unice.s3a.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.security.Principal;
import java.util.Collections;

/**
 * The type Account service.
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AccountService implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private Authentication authenticate(Account account) {
        return new UsernamePasswordAuthenticationToken(createUser(account),
            null,
            Collections.singleton(createAuthority(account))
        );
    }

    private GrantedAuthority createAuthority(Account account) {
        return new SimpleGrantedAuthority(account.getRole());
    }

    private User createUser(Account account) {
        return new User(account.getEmail(), account.getPassword(), Collections.singleton(createAuthority(account)));
    }

    /**
     * Current account.
     * @return the account
     */
    public Account current() {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        if (principal == null) {
            return null;
        }
        return accountRepository.findOneByEmail(principal.getName());
    }

    /**
     * Initialize.
     */
    @PostConstruct
    protected void initialize() {
        save(new Account("user", "demo", "ROLE_USER"));
        save(new Account("admin", "admin", "ROLE_AGENT"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findOneByEmail(username);
        if (account == null) {
            throw new UsernameNotFoundException("user not found");
        }
        return createUser(account);
    }

    /**
     * Save account.
     * @param email    the email
     * @param password the password
     * @param role     the role
     * @return the account
     */
    @Transactional
    public Account save(String email, String password, String role) {
        return save(new Account(email, password, role));
    }

    /**
     * Save account.
     * @param account the account
     * @return the account
     */
    @Transactional
    public Account save(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
        return account;
    }

    /**
     * Signin.
     * @param account the account
     */
    public void signin(Account account) {
        SecurityContextHolder.getContext().setAuthentication(authenticate(account));
    }
}
