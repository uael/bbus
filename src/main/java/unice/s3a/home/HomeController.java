package unice.s3a.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import unice.s3a.account.Account;
import unice.s3a.account.AccountRepository;

import java.security.Principal;

@Controller
public class HomeController {
	@Autowired
	private AccountRepository accountRepository;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Principal principal) {
		return principal != null ? "home/homeSignedIn" : "home/homeNotSignedIn";
	}

	/**
	 * Populate buses list.
	 * @return the list
	 */
	@ModelAttribute("account")
	public Account account(Principal principal) {
		return principal != null ? accountRepository.findOneByEmail(principal.getName()) : null;
	}
}
