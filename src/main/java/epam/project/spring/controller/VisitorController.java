package epam.project.spring.controller;

import epam.project.spring.dto.DishDto;
import epam.project.spring.entity.Dish;
import epam.project.spring.service.dish.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

import static epam.project.spring.util.Page.*;

/**
 * @author Aleksandr Ovcharenko
 */
@Controller
public class VisitorController {
    @Autowired
    MenuService menuService;

    @RequestMapping(value = "/")
    public String index() {
        return INDEX_PAGE;
    }

    @RequestMapping(value = "/menu")
    public String menu(Model model) {
        List<DishDto> dishes = menuService.showAllMenu();
        model.addAttribute("dish", dishes);
        return MENU_PAGE;
    }

    @RequestMapping(value = "/menu/byName")
    public String menu(HttpServletRequest request, Model model) {
        String name = request.getParameter("dishName");
        List<DishDto> dish = menuService.findDishByName(name);

        model.addAttribute("dish", dish);
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
