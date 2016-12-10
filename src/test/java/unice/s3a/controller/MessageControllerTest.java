package unice.s3a.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import unice.s3a.domain.BoxService;
import unice.s3a.domain.BusService;
import unice.s3a.config.WebAppConfigurationAware;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class MessageControllerTest extends WebAppConfigurationAware {
    @Autowired
    private BoxService boxService;
    @Autowired
    private BusService busService;

    @Before
    public void setUp() throws Exception {
        SecurityContextHolder.getContext().setAuthentication(
            new UsernamePasswordAuthenticationToken("producer", "producer")
        );
    }

    @After
    public void tearDown() throws Exception {
        SecurityContextHolder.clearContext();
    }

    @Test
    public void displaysMessageEmit() throws Exception {
        super.mockMvc.perform(get("/message/emit"))
            .andExpect(model().attributeExists("messageEmitForm"))
            .andExpect(view().name("message/emit"))
            .andExpect(content().string(
                allOf(
                    containsString("<title>Message - Emit</title>"),
                    containsString("<legend>Emit message to a bus</legend>")
                ))
            );
    }

    /**
     * Box create empty name.
     * @throws Exception the exception
     */
    @Test
    public void messgaeEmitEmptyContent() throws Exception {
        mockMvc.perform(post("/message/emit").param("bus", "Nice").param("box", "City").param("content", "").param("date", "30-11-2016"))
            .andExpect(view().name("message/emit"))
            .andExpect(content().string(
                allOf(
                    containsString("Form contains errors. Please try again."),
                    containsString("The value may not be empty!")
                ))
            );
    }

    /**
     * Box delete empty name.
     * @throws Exception the exception
     */
    @Test
    public void messageEmitDateWrongFormat() throws Exception {
        mockMvc.perform(post("/message/emit").param("bus", "Nice").param("box", "City").param("content", "Flic au coin de la rue").param("date", "30-p-2016"))
            .andExpect(view().name("message/emit"))
            .andExpect(content().string(
                allOf(
                        containsString("Form contains errors. Please try again."),
                        containsString("Failed to convert property value of type [java.lang.String] to required type [java.time.LocalDateTime] for property date")
                ))
            );
    }

    /**
     * Box delete empty name.
     * @throws Exception the exception
     */
    @Test
    public void messageEmitSuccess() throws Exception {
        mockMvc.perform(post("/message/emit").param("bus", "Nice Circulation").param("box", "Embouteillage")
                .param("content", "Flic au coin de la rue").param("date", "2016-12-30 10:56:38"))
            .andExpect(redirectedUrl("/message/emit"));
    }

    /**
     * Box delete empty name.
     * @throws Exception the exception
     */
    @Test
    public void messageEmitSuccessWithoutDate() throws Exception {
        mockMvc.perform(post("/message/emit").param("bus", "Nice Circulation").param("box", "Embouteillage")
                .param("content", "Flic au coin de la rue").param("date", ""))
            .andExpect(redirectedUrl("/message/emit"));
    }
}