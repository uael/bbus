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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class BusControllerTest extends WebAppConfigurationAware {
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
}