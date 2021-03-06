package unice.s3a.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import unice.s3a.model.Account;
import unice.s3a.domain.AccountService;
import unice.s3a.support.web.MessageHelper;

import javax.validation.Valid;

/**
 * The type Signup controller.
 */
@Controller
public class SignupController {
    private static final String SIGNUP_VIEW_NAME = "signup/signup";
    @Autowired private AccountService accountService;

    /**
     * Signup string.
     * @param model the model
     * @return the string
     */
    @RequestMapping(value = "signup")
    public String signup(Model model) {
        model.addAttribute(new SignupForm());
        return SIGNUP_VIEW_NAME;
    }

    /**
     * Signup string.
     * @param signupForm the signup form
     * @param errors     the errors
     * @param ra         the ra
     * @return the string
     */
    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public String signup(@Valid @ModelAttribute SignupForm signupForm, Errors errors, RedirectAttributes ra) {
        if (errors.hasErrors()) {
            return SIGNUP_VIEW_NAME;
        }
        Account account = accountService.save(signupForm.getEmail(), signupForm.getPassword(), signupForm.getRole());
        accountService.signin(account);
        MessageHelper.addSuccessAttribute(ra, "signup.success");
        return "redirect:/";
    }
}
