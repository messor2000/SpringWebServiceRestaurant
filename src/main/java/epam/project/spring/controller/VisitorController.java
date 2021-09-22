package epam.project.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static epam.project.spring.util.Page.*;

/**
 * @author Aleksandr Ovcharenko
 */
@Controller
public class VisitorController {

    @RequestMapping(value = "/")
    public String index() {
        return INDEX_PAGE;
    }

    @RequestMapping(value = "/menu")
    public String menu() {
        return MENU_PAGE;
    }




    @GetMapping(value = "/info/about")
    public String about() {
        return ABOUT_PAGE;
    }

    @GetMapping(value = "/info/recipes")
    public String recipes() {
        return RECIPES_PAGE;
    }

    @GetMapping(value = "/info/contact")
    public String contact() {
        return CONTACT_PAGE;
    }
}
