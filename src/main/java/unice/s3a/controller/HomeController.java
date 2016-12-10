package unice.s3a.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import unice.s3a.domain.AccountService;
import unice.s3a.model.Bus;
import unice.s3a.domain.BusService;
import unice.s3a.support.web.MessageHelper;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;

/**
 * The type Home controller.
 */
@Controller
public class HomeController {
    private final AccountService accountService;
    private final BusService busService;

    /**
     * Instantiates a new Home controller.
     * @param busService     the message service
     * @param accountService the account service
     */
    @Autowired
    public HomeController(final BusService busService, final AccountService accountService) {
        this.busService = busService;
        this.accountService = accountService;
    }

    /**
     * Index string.
     * @param principal the principal
     * @param model     the model
     * @return the string
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Principal principal, Model model) {
        if (principal == null) {
            return "home/homeNotSignedIn";
        }
        model.addAttribute(new SubscribeForm());
        model.addAttribute("buses", new ArrayList<>(busService.findAll().values()));
        return "home/homeSignedIn";
    }

    /**
     * Create string.
     * @param subscribeForm the bus delete form
     * @param ra            the ra
     * @return the string
     */
    @RequestMapping(value = "subscribe", method = RequestMethod.POST)
    public String subscribe(@Valid @ModelAttribute SubscribeForm subscribeForm, Errors errors, RedirectAttributes ra) {
        Bus bus = busService.findOne(subscribeForm.getBus());
        if (bus == null) {
            errors.rejectValue("bus", "bus", "Bus "+subscribeForm.getBus()+" does not exist.");
            return "home/homeSignedIn";
        }
        bus.subscribe(accountService.current());
        busService.save(bus);
        MessageHelper.addSuccessAttribute(ra,
            "subscribe.success",
            subscribeForm.getBus()
        );
        return "redirect:/";
    }

    /**
     * Create string.
     * @param subscribeForm the bus delete form
     * @param ra            the ra
     * @return the string
     */
    @RequestMapping(value = "unsubscribe", method = RequestMethod.POST)
    public String unsubscribe(@Valid @ModelAttribute SubscribeForm subscribeForm, Errors errors, RedirectAttributes ra) {
        Bus bus = busService.findOne(subscribeForm.getBus());
        if (bus == null) {
            errors.rejectValue("bus", "bus", "Bus "+subscribeForm.getBus()+" does not exist.");
            return "home/homeSignedIn";
        }
        bus.unsubscribe(accountService.current());
        busService.save(bus);
        MessageHelper.addSuccessAttribute(ra,
            "unsubscribe.success",
            subscribeForm.getBus()
        );
        return "redirect:/";
    }
}
