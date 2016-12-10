package unice.s3a.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import unice.s3a.domain.AccountService;
import unice.s3a.model.Account;

import java.security.Principal;

/**
 * The type Account controller.
 */
@Controller
class AccountController {
    private AccountService accountService;

    /**
     * Instantiates a new Account controller.
     * @param accountService the account service
     */
    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Account account.
     * @param id the id
     * @return the account
     */
    @RequestMapping(value = "account/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    @Secured("ROLE_AGENT")
    public Account account(@PathVariable("id") Long id) {
        return accountService.findOne(id);
    }

    /**
     * Current account account.
     * @param principal the principal
     * @return the account
     */
    @RequestMapping(value = "account/current", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    @Secured({ "ROLE_USER", "ROLE_AGENT", "ROLE_PRODUCER" })
    public Account currentAccount(Principal principal) {
        Assert.notNull(principal);
        return accountService.findOneByEmail(principal.getName());
    }
}
