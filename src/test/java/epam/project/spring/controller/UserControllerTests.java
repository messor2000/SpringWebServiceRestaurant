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
@TestPropertySource("/application-test.properties")
class UserControllerTests {
    @Autowired
    private MockMvc mockMvc;

//    @Test
//    @WithUserDetails("yulia.tokan.11@gmail.com")
//    public void getProductsTest() throws Exception {
//        String content = this.mockMvc.perform(get("/get_products"))
//                .andDo(print()).andReturn().getResponse().getContentAsString();
//
//        Product product = mapFromJson(content, Product.class);
//        assertEquals("Orange", product.getName());
//    }



    @Test
    void validateUserTest() throws Exception {
        this.mockMvc.perform(post("/sign/up")
                .param("username", "1%:!^&*%!")
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

//    @Test
//    @WithUserDetails("test")
//    void validPurseAmountTest() throws Exception {
//        this.mockMvc.perform(post("/user/product")
//                .param("name", "TestProduct")
//                .param("calories", "-10")
//                .param("protein", "5000")
//                .param("fats", "")
//                .param("carbohydrates", ""))
//                .andDo(print()).andExpect(status().is4xxClientError());
//    }

//    @Test
//    @WithUserDetails("test")
//    void createPurseTest() throws Exception {
//        this.mockMvc.perform(post("/user/purse")
////                .param("name", "TestProduct")
////                .param("calories", "-10")
////                .param("protein", "5000")
////                .param("fats", "")
////                .param("carbohydrates", ""))
//                .print().andExpect(status().is4xxClientError());
//    }

//    @Test
//    @WithUserDetails("test.user@gmail.com")
//    public void validProductTest() throws Exception {
//        this.mockMvc.perform(post("/user/product")
//                .param("name", "TestProduct")
//                .param("calories", "-10")
//                .param("protein", "5000")
//                .param("fats", "")
//                .param("carbohydrates", ""))
//                .andDo(print()).andExpect(status().is4xxClientError());
//    }
//
//    @Test
//    @WithUserDetails("test.user@gmail.com")
//    public void createProductTest() throws Exception {
//        this.mockMvc.perform(post("/user/product")
//                .param("name", "TestProduct")
//                .param("calories", "333")
//                .param("protein", "46.5")
//                .param("fats", "56.3")
//                .param("carbohydrates", "38.6"))
//                .andDo(print()).andExpect(status().is3xxRedirection());
//    }
//
//    @Test
//    @WithUserDetails("test.user@gmail.com")
//    public void createMealsTest() throws Exception {
//        this.mockMvc.perform(post("/user/meals")
//                .param("food", "27")
//                .param("weight", "300")
//                .param("eat_period", "13"))
//                .andDo(print()).andExpect(status().isOk());
//    }
}
