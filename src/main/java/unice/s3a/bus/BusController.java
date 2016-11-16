package unice.s3a.bus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import unice.s3a.account.Account;
import unice.s3a.account.AccountRepository;
import unice.s3a.support.web.MessageHelper;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Bus controller.
 */
@Controller
class BusController {
    private static final String CREATE = "bus/create";
    private static final String DELETE = "bus/delete";
    private static final String LIST = "bus/list";
    private final BusService busService;
    @Autowired
    private AccountRepository accountRepository;

    /**
     * Instantiates a new Bus controller.
     * @param busService the bus service
     */
    @Autowired
    public BusController(final BusService busService) {
        this.busService = busService;
    }

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
     * Create string.
     * @param busCreateForm the bus create form
     * @param errors        the errors
     * @param ra            the ra
     * @return the string
     */
    @RequestMapping(value = CREATE, method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute BusCreateForm busCreateForm, Errors errors, RedirectAttributes ra) {
        if (errors.hasErrors()) {
            return CREATE;
        }
        busService.save(busCreateForm.createBus());
        MessageHelper.addSuccessAttribute(ra, CREATE+".success", busCreateForm.getName());
        return "redirect:/"+CREATE;
    }

    /**
     * Create string.
     * @param model the model
     * @return the string
     */
    @RequestMapping(value = CREATE)
    public String create(Model model) {
        model.addAttribute(new BusCreateForm());
        return CREATE;
    }

    /**
     * Delete string.
     * @param model the model
     * @return the string
     */
    @RequestMapping(value = DELETE)
    public String delete(Model model) {
        model.addAttribute(new BusDeleteForm());
        return DELETE;
    }

    @RequestMapping(value = LIST)
    public String list(Model model) {
        return LIST;
    }

    /**
     * Create string.
     * @param busDeleteForm the bus delete form
     * @param errors        the errors
     * @param ra            the ra
     * @return the string
     */
    @RequestMapping(value = DELETE, method = RequestMethod.POST)
    public String delete(@Valid @ModelAttribute BusDeleteForm busDeleteForm, Errors errors, RedirectAttributes ra) {
        if (errors.hasErrors()) {
            return DELETE;
        }
        busService.delete(busDeleteForm.getBus());
        MessageHelper.addSuccessAttribute(ra, DELETE+".success", busDeleteForm.getBus().getName());
        return "redirect:/"+DELETE;
    }

    /**
     * Populate buses list.
     * @return the list
     */
    @ModelAttribute("buses")
    public List<Bus> populateBuses() {
        return new ArrayList<>(busService.findAll().values());
    }
}
