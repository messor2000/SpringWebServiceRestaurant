package epam.project.spring.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Aleksandr Ovcharenko
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("manager")
@TestPropertySource("/application-test.properties")
class AdminControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void createNewDishTest() throws Exception {
        this.mockMvc.perform(post("/admin/addDish")
                .param("name", "new dish")
                .param("price", "12")
                .param("category", "desert")
                .param("amount", "12"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void validateNewDishTest() throws Exception {
        this.mockMvc.perform(post("/admin/addDish")
                .param("name", "<script>alert('200')</script>")
                .param("price", "12")
                .param("category", "desert")
                .param("amount", "12"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}
