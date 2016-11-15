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
import unice.s3a.bus.BusCreateForm;
import unice.s3a.bus.BusDeleteForm;
import unice.s3a.bus.BusService;
import unice.s3a.support.web.MessageHelper;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
class MessageController {

    static final String EMIT = "message/create";

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = EMIT)
    public String emit(Model model) {
        model.addAttribute(new MessageCreateForm());
        return EMIT;
    }

    @RequestMapping(value = EMIT, method = RequestMethod.POST)
    public String emit(@Valid @ModelAttribute MessageCreateForm messageCreateForm, Errors errors, RedirectAttributes ra) {
        if (errors.hasErrors()) {
            return EMIT;
        }
        messageService.save(messageCreateForm.createMessage());
        MessageHelper.addSuccessAttribute(ra, EMIT+".success");
        return "redirect:/";
    }
}
