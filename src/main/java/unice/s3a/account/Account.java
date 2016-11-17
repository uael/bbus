package unice.s3a.account;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.Instant;

/**
 * The type Account.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "ACCOUNT")
public class Account implements java.io.Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String email;

    @JsonIgnore private
    String password;

    private String role = "ROLE_USER";
    private Instant created;

    /**
     * Instantiates a new Account.
     */
    protected Account() { }

    /**
     * Instantiates a new Account.
     * @param email    the email
     * @param password the password
     * @param role     the role
     */
    public Account(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.created = Instant.now();
    }

    /**
     * Gets created.
     * @return the created
     */
    public Instant getCreated() {
        return created;
    }

    /**
     * Gets email.
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets id.
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets password.
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets role.
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets role.
     * @param role the role
     */
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) { return true; }
        if (!(o instanceof Account)) { return false; }
        Account account = (Account) o;
        return getId().equals(account.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
