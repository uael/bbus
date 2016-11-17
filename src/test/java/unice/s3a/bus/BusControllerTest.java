package unice.s3a.bus;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import unice.s3a.config.WebAppConfigurationAware;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * The type Bus controller test.
 */
public class BusControllerTest extends WebAppConfigurationAware {
    /**
     * Sets up.
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        SecurityContextHolder.getContext().setAuthentication(
            new UsernamePasswordAuthenticationToken("admin", "admin")
        );
    }

    /**
     * Tear down.
     * @throws Exception the exception
     */
    @After
    public void tearDown() throws Exception {
        SecurityContextHolder.clearContext();
    }

    /**
     * Displays bus create.
     * @throws Exception the exception
     */
    @Test
    public void displaysBusCreate() throws Exception {
        super.mockMvc.perform(get("/bus/create"))
            .andExpect(model().attributeExists("busCreateForm"))
            .andExpect(view().name("bus/create"))
            .andExpect(content().string(
                allOf(
                    containsString("<title>Bus - Create</title>"),
                    containsString("<legend>Create new bus</legend>")
                ))
            );
    }

    /**
     * Bus create empty name.
     * @throws Exception the exception
     */
    @Test
    public void busCreateEmptyName() throws Exception {
        mockMvc.perform(post("/bus/create").param("name", ""))
            .andExpect(view().name("bus/create"))
            .andExpect(content().string(
                allOf(
                    containsString("Form contains errors. Please try again."),
                    containsString("The value may not be empty!")
                ))
            );
    }

    /**
     * Bus create already exist.
     * @throws Exception the exception
     */
    @Test
    public void busCreateAlreadyExist() throws Exception {
        mockMvc.perform(post("/bus/create").param("name", "Nice"))
            .andExpect(view().name("bus/create"))
            .andExpect(content().string(
                allOf(
                    containsString("Form contains errors. Please try again."),
                    containsString("A bus already exists for this name.")
                ))
            );
    }

    /**
     * Displays bus delete.
     * @throws Exception the exception
     */
    @Test
    public void displaysBusDelete() throws Exception {
        super.mockMvc.perform(get("/bus/delete"))
            .andExpect(model().attributeExists("busDeleteForm"))
            .andExpect(view().name("bus/delete"))
            .andExpect(content().string(
                allOf(
                        containsString("<title>Bus - Delete</title>"),
                        containsString("<legend>Delete bus</legend>")
                ))
            );
    }

    /**
     * Displays bus list.
     * @throws Exception the exception
     */
    @Test
    public void displaysBusList() throws Exception {
        super.mockMvc.perform(get("/bus/list"))
            .andExpect(view().name("bus/list"))
            .andExpect(content().string(
                allOf(
                        containsString("<title>Bus - List</title>"),
                        containsString("<th>Name</th>")
                ))
            );
    }

    /**
     * Bus delete empty name.
     * @throws Exception the exception
     */
    @Test
    public void busDeleteEmptyName() throws Exception {
        mockMvc.perform(post("/bus/delete"))
                .andExpect(view().name("bus/delete"))
                .andExpect(content().string(
                        allOf(
                                containsString("Form contains errors. Please try again."),
                                containsString("The value may not be empty!")
                        ))
                );
    }

    /**
     * Bus delete successfully.
     * @throws Exception the exception
     */
    @Test
    public void busDeleteSuccessfully() throws Exception {
        mockMvc.perform(post("/bus/delete").param("bus", "Nice"))
                .andExpect(redirectedUrl("/bus/delete"));
    }
}