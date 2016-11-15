package unice.s3a.box;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import unice.s3a.bus.Bus;
import unice.s3a.bus.BusRepository;
import unice.s3a.bus.BusService;
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
    private final BoxRepository boxRepository;
    private final BoxService boxService;
    private final BusRepository busRepository;
    private final BusService busService;

    /**
     * Instantiates a new Box controller.
     * @param busRepository the bus service
     * @param boxService    the box service
     * @param boxRepository the box repository
     * @param busService    the bus service
     */
    @Autowired
    public BoxController(final BusRepository busRepository, final BoxService boxService, final BoxRepository
        boxRepository, final BusService busService) {
        this.busRepository = busRepository;
        this.boxService = boxService;
        this.boxRepository = boxRepository;
        this.busService = busService;
    }

    /**
     * Create string.
     * @param model the model
     * @return the string
     */
    @RequestMapping(value = CREATE)
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
    public String create(@Valid @ModelAttribute BoxCreateForm boxCreateForm, Errors errors, RedirectAttributes ra) {
        if (errors.hasErrors()) {
            return CREATE;
        }
        busService.addBox(boxCreateForm.getBus().getName(), boxService.save(new Box(boxCreateForm.getName())));
        MessageHelper.addSuccessAttribute(ra, CREATE+".success");
        return "redirect:"+CREATE;
    }

    /**
     * Create string.
     * @param boxDeleteForm the box delete form
     * @param errors        the errors
     * @param ra            the ra
     * @return the string
     */
    @RequestMapping(value = DELETE, method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute BoxDeleteForm boxDeleteForm, Errors errors, RedirectAttributes ra) {
        if (errors.hasErrors()) {
            return DELETE;
        }
        boxRepository.delete(boxDeleteForm.getBox());
        MessageHelper.addSuccessAttribute(ra, CREATE+".success");
        return "redirect:"+DELETE;
    }

    /**
     * Delete string.
     * @param model the model
     * @return the string
     */
    @RequestMapping(value = DELETE)
    public String delete(Model model) {
        model.addAttribute(new BoxDeleteForm());
        return DELETE;
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
