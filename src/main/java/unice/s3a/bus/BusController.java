package unice.s3a.bus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import unice.s3a.support.web.MessageHelper;

import javax.validation.Valid;

@Controller
class BusController {

    private static final String CREATE = "bus/create";

    @Autowired
    private BusService busService;

    @RequestMapping(value = CREATE)
    public String create(Model model) {
        model.addAttribute(new BusCreateForm());
        return CREATE;
    }

    @RequestMapping(value = CREATE, method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute BusCreateForm busCreateForm, Errors errors, RedirectAttributes ra) {
        if (errors.hasErrors()) {
            return CREATE;
        }
        busService.save(busCreateForm.createBus());
        MessageHelper.addSuccessAttribute(ra, CREATE+".success");
        return "redirect:/";
    }
}
