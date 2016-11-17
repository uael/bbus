package unice.s3a.box;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import unice.s3a.account.Account;
import unice.s3a.account.AccountRepository;
import unice.s3a.bus.Bus;
import unice.s3a.bus.BusService;
import unice.s3a.support.web.MessageHelper;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Box controller.
 */
@Controller
class BoxController {
    private static final String CREATE = "box/create";
    private static final String DELETE = "box/delete";
    private static final String LIST = "box/list";
    private final AccountRepository accountRepository;
    private final BoxService boxService;
    private final BusService busService;

    /**
     * Instantiates a new Box controller.
     * @param boxService        the box service
     * @param busService        the bus service
     * @param accountRepository the account repository
     */
    @Autowired
    public BoxController(final BoxService boxService, final BusService busService, final AccountRepository accountRepository) {
        this.boxService = boxService;
        this.busService = busService;
        this.accountRepository = accountRepository;
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
     * @param model the model
     * @return the string
     */
    @RequestMapping(value = CREATE)
    @Secured("ROLE_AGENT")
    public String create(Model model) {
        model.addAttribute(new BoxCreateForm());
        return CREATE;
    }

    /**
     * Create string.
     * @param boxCreateForm the bus create form
     * @param errors        the errors
     * @param ra            the ra
     * @return the string
     */
    @RequestMapping(value = CREATE, method = RequestMethod.POST)
    @Secured("ROLE_AGENT")
    public String create(@Valid @ModelAttribute BoxCreateForm boxCreateForm, Errors errors, RedirectAttributes ra) {
        if (errors.hasErrors()) {
            return CREATE;
        }
        busService.addBox(boxCreateForm.getBus(), boxService.save(boxCreateForm.getName()));
        MessageHelper.addSuccessAttribute(ra, CREATE+".success", boxCreateForm.getName());
        return "redirect:/"+CREATE;
    }

    /**
     * Create string.
     * @param boxDeleteForm the box delete form
     * @param errors        the errors
     * @param ra            the ra
     * @return the string
     */
    @RequestMapping(value = DELETE, method = RequestMethod.POST)
    @Secured("ROLE_AGENT")
    public String delete(@Valid @ModelAttribute BoxDeleteForm boxDeleteForm, Errors errors, RedirectAttributes ra) {
        if (errors.hasErrors()) {
            return DELETE;
        }
        busService.delete(boxDeleteForm.getBox());
        boxService.delete(boxDeleteForm.getBox());
        MessageHelper.addSuccessAttribute(ra, CREATE+".success", boxDeleteForm.getBox().getName());
        return "redirect:/"+DELETE;
    }

    /**
     * Delete string.
     * @param model the model
     * @return the string
     */
    @RequestMapping(value = DELETE)
    @Secured("ROLE_AGENT")
    public String delete(Model model) {
        model.addAttribute(new BoxDeleteForm());
        return DELETE;
    }

    /**
     * List string.
     * @return the string
     */
    @RequestMapping(value = LIST)
    public String list() {
        return LIST;
    }

    /**
     * Populate buses list.
     * @param busName the bus name
     * @return the list
     */
    @RequestMapping(value = "boxes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Box> populateBoxes(@RequestParam(value = "busName") String busName) {
        return busService.getBusBoxes(busName);
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
