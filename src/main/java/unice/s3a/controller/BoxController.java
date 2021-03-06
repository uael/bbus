package unice.s3a.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import unice.s3a.model.Box;
import unice.s3a.model.Bus;
import unice.s3a.domain.BoxService;
import unice.s3a.domain.BusService;
import unice.s3a.support.web.MessageHelper;

import javax.validation.Valid;
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
    private final BoxService boxService;
    private final BusService busService;

    /**
     * Instantiates a new Box controller.
     * @param boxService        the box service
     * @param busService        the bus service
     */
    @Autowired
    public BoxController(final BoxService boxService, final BusService busService) {
        this.boxService = boxService;
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
        if (busService.findOne(boxCreateForm.getBus()).getBoxes().containsKey(boxCreateForm.getName())) {
            errors.rejectValue("name", "name", "A box already exists for this name.");
            return CREATE;
        }
        busService.addBox(boxCreateForm.getBus(), new Box(boxCreateForm.getName()));
        MessageHelper.addSuccessAttribute(ra, CREATE+".success", boxCreateForm.getName());
        return "redirect:/"+CREATE;
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
        boxService.delete(boxDeleteForm.getBox());
        MessageHelper.addSuccessAttribute(ra, CREATE+".success", boxDeleteForm.getBox());
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

    /**
     * Populate buses list.
     * @param busName the bus name
     * @return the list
     */
    @RequestMapping(value = "boxes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Box> boxes(@RequestParam(value = "busName") String busName) {
        return busService.getBusBoxes(busName);
    }
}
