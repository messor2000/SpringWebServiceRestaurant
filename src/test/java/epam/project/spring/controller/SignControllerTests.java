package epam.project.spring.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Aleksandr Ovcharenko
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SignControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void securityTest() throws Exception {
        this.mockMvc.perform(get("/user/purse"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/sign/up"));
    }

    @Test
    public void badCredentialsTest() throws Exception {
        this.mockMvc.perform(formLogin().loginProcessingUrl("/sign/in").user("testUser").password("qwe12345"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/error"));
    }


    @Test
    public void successLoginTest() throws Exception {
        this.mockMvc.perform(post("/sign/in")
                .param("username", "testUser")
                .param("password", "qwe12345"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/menu"));
    }

//    @Test
//    public void validateUserTest() throws Exception {
//        this.mockMvc.perform(post("/sign/up")
//                .param("login", "yulka-pulka")
//                .param("password", "1234"))
//                .andDo(print()).andExpect(status().is4xxClientError());
//    }
}