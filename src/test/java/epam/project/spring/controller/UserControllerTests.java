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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Aleksandr Ovcharenko
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
//@WithUserDetails("manager")
@TestPropertySource("/application-test.properties")
class UserControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void validateUserTest() throws Exception {
        this.mockMvc.perform(post("/sign/up")
                .param("username", "<script>alert('200')</script>")
                .param("password", "1234"))
                .andDo(print()).andExpect(status().is4xxClientError());
    }

    @Test
    void createUserTest() throws Exception {
        this.mockMvc.perform(post("/sign/up")
                .param("username", "testtest")
                .param("password", "qwe12345"))
                .andDo(print()).andExpect(status().is3xxRedirection());
    }

    @Test
    @WithUserDetails("username")
    void validPurseAmountTest() throws Exception {
        this.mockMvc.perform(post("/user/topUpPurse")
                .param("amount", "-10"))
                .andDo(print()).andExpect(status().is4xxClientError());
    }

    @Test
    @WithUserDetails("username")
    void showUserPurse() throws Exception {
        this.mockMvc.perform(get("/user/purse"))
                .andDo(print()).andExpect(status().is2xxSuccessful());
    }

    @Test
    @WithUserDetails("username")
    void topUpPurse() throws Exception {
        this.mockMvc.perform(post("/user/toUpPurse")
                .param("amount", "10"))
                .andDo(print()).andExpect(status().is2xxSuccessful());
    }


}
