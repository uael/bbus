package unice.s3a.box;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import unice.s3a.config.WebAppConfigurationAware;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class BoxControllerTest extends WebAppConfigurationAware {
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
                    containsString("<legend>Delete new box</legend>")
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
}