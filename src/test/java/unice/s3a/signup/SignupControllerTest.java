package unice.s3a.signup;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import unice.s3a.config.WebAppConfigurationAware;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class SignupControllerTest extends WebAppConfigurationAware {

    private static String SEC_CONTEXT_ATTR = HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

    @Test
    public void displaysSignupForm() throws Exception {
        mockMvc.perform(get("/signup"))
            .andExpect(model().attributeExists("signupForm"))
            .andExpect(view().name("signup/signup"))
            .andExpect(content().string(
                allOf(
                    containsString("<title>Signup</title>"),
                    containsString("<legend>Please Sign up</legend>")
                ))
            );
    }

    @Test
    public void signUpEmptyMail() throws Exception {
        mockMvc.perform(post("/signup").param("email", "").param("role", "ROLE_AGENT")
            .param("password", "etib"))
            .andExpect(r -> Assert.assertNull(r.getRequest().getSession().getAttribute(SEC_CONTEXT_ATTR)))
            .andExpect(content().string(
                allOf(
                    containsString("Sign up errors. Please try again."),
                    containsString("The value may not be empty!")
                ))
            );
    }

    @Test
    public void signUpEmptyPassword() throws Exception {
        mockMvc.perform(post("/signup")
            .param("email", "azerty@gmail.com")
            .param("role", "ROLE_AGENT")
            .param("password", ""))
            .andExpect(r -> Assert.assertNull(r.getRequest().getSession().getAttribute(SEC_CONTEXT_ATTR)))
            .andExpect(content().string(
                allOf(
                    containsString("Sign up errors. Please try again."),
                    containsString("The value may not be empty!")
                ))
            );
    }

    @Test
    public void signUpValidControl() throws Exception {
        mockMvc.perform(post("/signup")
            .param("email", "azerty@gmail.com")
            .param("role", "ROLE_AGENT")
            .param("password", "aze"))
            .andExpect(redirectedUrl("/"));
    }
}