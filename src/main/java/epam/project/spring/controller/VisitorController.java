package epam.project.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static epam.project.spring.util.Page.*;

/**
 * @author Aleksandr Ovcharenko
 */
@Controller
public class VisitorController {

    @RequestMapping(value = "/")
    public String index(HttpSession session, Model model, HttpServletRequest request) {
        return INDEX_PAGE;
    }

    @GetMapping(value = "/info/about")
    public String about(HttpSession session, Model model) {
        return ABOUT_PAGE;
    }

    @GetMapping(value = "/info/recipes")
    public String recipes(HttpSession session, Model model) {
        return RECIPES_PAGE;
    }

    @GetMapping(value = "/info/contact")
    public String contact(HttpSession session, Model model) {
        return CONTACT_PAGE;
    }
}
