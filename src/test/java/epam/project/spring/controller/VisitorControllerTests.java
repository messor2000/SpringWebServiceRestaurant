package epam.project.spring.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Aleksandr Ovcharenko
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("test")
class VisitorControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void shouldReturnMenu() throws Exception {
        this.mockMvc.perform(get("/menu")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void shouldReturnMenuByName() throws Exception {
        this.mockMvc.perform(get("/menu/byName")
                .param("dish", "Cake"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void shouldReturnMenuFromHighToLow() throws Exception {
        this.mockMvc.perform(get("/menu/fromHighToLow")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void shouldReturnMenuFromLowToHigh() throws Exception {
        this.mockMvc.perform(get("/menu/fromLowToHigh")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void shouldReturnMenuByCategoryFastFood() throws Exception {
        this.mockMvc.perform(get("/menu/fastFood")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void shouldReturnMenuByCategoryHealthyFood() throws Exception {
        this.mockMvc.perform(get("/menu/healthyFood")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void shouldReturnMenuByCategoryDesert() throws Exception {
        this.mockMvc.perform(get("/user/order"))
                .andDo(print())
                .andExpect(authenticated());
    }

    @Test
    void securityTest() throws Exception {
        this.mockMvc.perform(get("/user/order"))
                .andDo(print())
                .andExpect(authenticated());
    }
}

