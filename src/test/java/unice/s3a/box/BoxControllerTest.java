package unice.s3a.box;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import unice.s3a.bus.BusService;
import unice.s3a.config.WebAppConfigurationAware;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class BoxControllerTest extends WebAppConfigurationAware {
    @Autowired
    private BoxService boxService;
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

    @Test
    public void displaysBoxCreate() throws Exception {
        super.mockMvc.perform(get("/box/create"))
            .andExpect(model().attributeExists("boxCreateForm"))
            .andExpect(view().name("box/create"))
            .andExpect(content().string(
                allOf(
                    containsString("<title>Box - Create</title>"),
                    containsString("<legend>Create new box</legend>")
                ))
            );
    }

    @Test
    public void displaysBoxDelete() throws Exception {
        super.mockMvc.perform(get("/box/delete"))
            .andExpect(model().attributeExists("boxDeleteForm"))
            .andExpect(view().name("box/delete"))
            .andExpect(content().string(
                allOf(
                    containsString("<title>Box - Delete</title>"),
                    containsString("<legend>Delete a box</legend>")
                ))
            );
    }

    @Test
    public void displaysBoxList() throws Exception {
        super.mockMvc.perform(get("/box/list"))
            .andExpect(view().name("box/list"))
            .andExpect(content().string(
                allOf(
                    containsString("<title>Box - List</title>"),
                    containsString("<th>Name</th>")
                ))
            );
    }

    /**
     * Bus create already exist.
     * @throws Exception the exception
     */
    @Test
    public void boxCreateAlreadyExist() throws Exception {
        mockMvc.perform(post("/box/create").param("bus", "Nice Circulation").param("name", "Embouteillage"))
            .andExpect(view().name("box/create"))
            .andExpect(content().string(
                allOf(
                    containsString("Form contains errors. Please try again."),
                    containsString("A box already exists for this name.")
                ))
            );
    }

    /**
     * Box create empty name.
     * @throws Exception the exception
     */
    @Test
    public void boxCreateEmptyName() throws Exception {
        mockMvc.perform(post("/box/create").param("bus", "Nice").param("name", ""))
            .andExpect(view().name("box/create"))
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
    public void boxDeleteEmptyName() throws Exception {
        mockMvc.perform(post("/box/delete").param("bus", "Nice").param("name", ""))
            .andExpect(view().name("box/delete"))
            .andExpect(content().string(
                allOf(
                    containsString("Form contains errors. Please try again."),
                    containsString("The value may not be empty!")
                ))
            );
    }

    /**
     * Box delete successfully.
     * @throws Exception the exception
     */
    @Test
    public void boxDeleteSuccessfully() throws Exception {
        mockMvc.perform(post("/box/delete").param("bus", "Nice Circulation").param("box", "point"))
            .andExpect(redirectedUrl("/box/delete"));
    }
}