package unice.s3a.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import unice.s3a.account.Account;
import unice.s3a.account.AccountRepository;

import java.security.Principal;

/**
 * The type Home controller.
 */
@Controller
public class HomeController {
    @Autowired private AccountRepository accountRepository;

    /**
     * Populate buses list.
     * @param principal the principal
     * @return the list
     */
    @ModelAttribute("account")
    public Account account(Principal principal) {
        return principal != null ? accountRepository.findOneByEmail(principal.getName()) : null;
    }

    /**
     * Index string.
     * @param principal the principal
     * @return the string
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Principal principal) {
        return principal != null ? "home/homeSignedIn" : "home/homeNotSignedIn";
    }
}
