package unice.s3a.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * The type Account controller.
 */
@Controller
class AccountController {
    private AccountRepository accountRepository;

    /**
     * Instantiates a new Account controller.
     * @param accountRepository the account repository
     */
    @Autowired
    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * Account account.
     * @param id the id
     * @return the account
     */
    @RequestMapping(value = "account/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    @Secured("ROLE_ADMIN")
    public Account account(@PathVariable("id") Long id) {
        return accountRepository.findOne(id);
    }

    /**
     * Current account account.
     * @param principal the principal
     * @return the account
     */
    @RequestMapping(value = "account/current", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    @Secured({ "ROLE_USER", "ROLE_ADMIN" })
    public Account currentAccount(Principal principal) {
        Assert.notNull(principal);
        return accountRepository.findOneByEmail(principal.getName());
    }
}
