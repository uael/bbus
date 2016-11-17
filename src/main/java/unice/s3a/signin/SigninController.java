package unice.s3a.signin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The type Signin controller.
 */
@Controller
public class SigninController {
    /**
     * Signin string.
     * @return the string
     */
    @RequestMapping(value = "signin")
    public String signin() {
        return "signin/signin";
    }
}
