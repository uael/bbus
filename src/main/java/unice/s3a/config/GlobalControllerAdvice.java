package unice.s3a.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import unice.s3a.account.AccountService;

/**
 * The type Global controller advice.
 */
@ControllerAdvice
public class GlobalControllerAdvice {
    private final AccountService accountService;

    /**
     * Instantiates a new Global controller advice.
     * @param accountService the account service
     */
    @Autowired
    public GlobalControllerAdvice(final AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Global attributes.
     * @param model the model
     */
    @ModelAttribute
    public void globalAttributes(Model model) {
        model.addAttribute("account", accountService.current());
    }
}
