package unice.s3a.home;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import unice.s3a.account.AccountService;
import unice.s3a.bus.BusService;
import unice.s3a.config.WebAppConfigurationAware;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class HomeControllerTest extends WebAppConfigurationAware {
    @Autowired
    private AccountService accountService;
    @Autowired
    private BusService busService;

    @Before
    public void setUp() throws Exception {
        SecurityContextHolder.getContext().setAuthentication(
            new UsernamePasswordAuthenticationToken("admin", "admin")
        );
    }

    @After
    public void tearDown() throws Exception {
        SecurityContextHolder.clearContext();
    }

    /**
     * Bus create already exist.
     * @throws Exception the exception
     */
    @Test
    public void subscribeBusSuccefully() throws Exception {
        mockMvc.perform(post("/subscribe").param("bus", "Nice Circulation"))
                .andExpect(redirectedUrl("/"));
    }

    /**
     * Bus create already exist.
     * @throws Exception the exception
     */
    @Test
    public void subscribeBusUnsuccefully() throws Exception {
        mockMvc.perform(post("/unsubscribe").param("bus", "Nice Circulation"))
                .andExpect(redirectedUrl("/"));
    }
}