package unice.s3a.signup;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import unice.s3a.account.Account;

import javax.validation.constraints.NotNull;

/**
 * The type Signup form.
 */
public class SignupForm {
    private static final String EMAIL_MESSAGE = "{email.message}";
    private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";
    @NotBlank(message = SignupForm.NOT_BLANK_MESSAGE)
    @Email(message = SignupForm.EMAIL_MESSAGE)
    private String email;
    @NotBlank(message = SignupForm.NOT_BLANK_MESSAGE) private String password;
    @NotBlank(message = SignupForm.NOT_BLANK_MESSAGE)
    @NotNull(message = SignupForm.NOT_BLANK_MESSAGE)
    private String role;

    /**
     * Create account account.
     * @return the account
     */
    public Account createAccount() {
        return new Account(getEmail(), getPassword(), getRole());
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
    public void setRole(final String role) {
        this.role = role;
    }
}
