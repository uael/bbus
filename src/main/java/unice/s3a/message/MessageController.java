package unice.s3a.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import unice.s3a.bus.Bus;
import unice.s3a.bus.BusService;
import unice.s3a.support.web.MessageHelper;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Message controller.
 */
@Controller
class MessageController {
    private static final String EMIT = "message/emit";
    private final BusService busService;
    private final MessageService messageService;

    /**
     * Instantiates a new Message controller.
     * @param messageService the message service
     * @param busService     the bus service
     */
    @Autowired
    public MessageController(final MessageService messageService, final BusService busService) {
        this.messageService = messageService;
        this.busService = busService;
    }

    /**
     * Emit string.
     * @param model the model
     * @return the string
     */
    @Secured("ROLE_PRODUCER")
    @RequestMapping(value = EMIT)
    public String emit(Model model) {
        model.addAttribute(new MessageEmitForm());
        return EMIT;
    }

    /**
     * Emit string.
     * @param messageEmitForm the message emit form
     * @param errors          the errors
     * @param ra              the ra
     * @return the string
     */
    @Secured("ROLE_PRODUCER")
    @RequestMapping(value = EMIT, method = RequestMethod.POST)
    public String emit(@Valid @ModelAttribute MessageEmitForm messageEmitForm, Errors errors, RedirectAttributes ra) {
        if (errors.hasErrors()) {
            return EMIT;
        }
        busService.emit(messageEmitForm.getBox(), messageEmitForm.getContent(), messageEmitForm.getDate());
        MessageHelper.addSuccessAttribute(ra, EMIT+".success", messageEmitForm.getContent());
        return "redirect:/"+EMIT;
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
