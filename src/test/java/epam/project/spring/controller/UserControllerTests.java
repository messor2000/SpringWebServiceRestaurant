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
    @WithUserDetails("testtest")
    void showUserPurseTest() throws Exception {
        this.mockMvc.perform(get("/user/purse"))
                .andDo(print()).andExpect(status().is2xxSuccessful());
    }

    @Test
    @WithUserDetails("testtest")
    void showOrderTest() throws Exception {
        this.mockMvc.perform(get("/user/showOrder"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @WithUserDetails("testtest")
    void putInOrderTest() throws Exception {
        this.mockMvc.perform(post("/user/putInBucket")
                .param("name", "Pizza"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }


    @Test
    @WithUserDetails("testtest")
    void payWithoutMoneyTest() throws Exception {
        this.mockMvc.perform(post("/user/pay"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithUserDetails("testtest")
    void topUpPurseTest() throws Exception {
        this.mockMvc.perform(post("/user/toUpPurse")
                .param("amount", "100"))
                .andDo(print()).andExpect(status().is3xxRedirection());
    }

    @Test
    @WithUserDetails("testtest")
    void payTest() throws Exception {
        this.mockMvc.perform(post("/user/pay"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }


}
