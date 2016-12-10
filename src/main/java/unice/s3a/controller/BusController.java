package unice.s3a.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import unice.s3a.domain.BusService;
import unice.s3a.model.Bus;
import unice.s3a.support.web.MessageHelper;

import javax.validation.Valid;
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

    /**
     * Instantiates a new Bus controller.
     * @param busService        the bus service
     */
    @Autowired
    public BusController(final BusService busService) {
        this.busService = busService;
    }

    /**
     * Populate buses list.
     * @return the list
     */
    @ModelAttribute("buses")
    public List<Bus> attributeBuses() {
        return new ArrayList<>(busService.findAll().values());
    }

    /**
     * Create string.
     * @param model the model
     * @return the string
     */
    @Secured("ROLE_AGENT")
    @RequestMapping(value = CREATE)
    public String create(Model model) {
        model.addAttribute(new BusCreateForm());
        return CREATE;
    }

    /**
     * Create string.
     * @param busCreateForm the bus create form
     * @param errors        the errors
     * @param ra            the ra
     * @return the string
     */
    @Secured("ROLE_AGENT")
    @RequestMapping(value = CREATE, method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute BusCreateForm busCreateForm, Errors errors, RedirectAttributes ra) {
        if (errors.hasErrors()) {
            return CREATE;
        }
        if (busService.has(busCreateForm.getName())) {
            errors.rejectValue("name", "name", "A bus already exists for this name.");
            return CREATE;
        }
        busService.save(busCreateForm.getName());
        MessageHelper.addSuccessAttribute(ra, CREATE+".success", busCreateForm.getName());
        return "redirect:/"+CREATE;
    }

    /**
     * Delete string.
     * @param model     the model
     * @return the string
     */
    @Secured("ROLE_AGENT")
    @RequestMapping(value = DELETE)
    public String delete(Model model) {
        model.addAttribute(new BusDeleteForm());
        return DELETE;
    }

    /**
     * Delete string.
     * @param busDeleteForm the bus delete form
     * @param errors        the errors
     * @param ra            the ra
     * @return the string
     */
    @Secured("ROLE_AGENT")
    @RequestMapping(value = DELETE, method = RequestMethod.POST)
    public String delete(@Valid @ModelAttribute BusDeleteForm busDeleteForm, Errors errors, RedirectAttributes ra) {
        if (errors.hasErrors()) {
            return DELETE;
        }
        busService.delete(busDeleteForm.getBus());
        MessageHelper.addSuccessAttribute(ra, DELETE+".success", busDeleteForm.getBus());
        return "redirect:/"+DELETE;
    }

    /**
     * List string.
     * @return the string
     */
    @RequestMapping(value = LIST)
    public String list(Model model) {
        model.addAttribute("buses", new ArrayList<>(busService.findAll().values()));
        return LIST;
    }
}
