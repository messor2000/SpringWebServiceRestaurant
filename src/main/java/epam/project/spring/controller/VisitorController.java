package epam.project.spring.controller;

import epam.project.spring.dto.DishDto;
import epam.project.spring.entity.Dish;
import epam.project.spring.service.dish.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

import static epam.project.spring.util.Constants.PARAM_DISH;
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
        model.addAttribute(PARAM_DISH, dishes);
        return MENU_PAGE;
    }

    @RequestMapping(value = "/menu/byName")
    public String menu(HttpServletRequest request, Model model) {
        String name = request.getParameter("dishName");
        List<DishDto> dish = menuService.findDishByName(name);

        model.addAttribute(PARAM_DISH, dish);
        return MENU_PAGE;
    }

    @RequestMapping(value = "/menu/fromHighToLow")
    public String menuFormHighToLow(Model model) {
        List<DishDto> dish = menuService.showAllMenuFromHighPriceToLow();

        model.addAttribute(PARAM_DISH, dish);
        return MENU_PAGE;
    }

    @RequestMapping(value = "/menu/fromLowToHigh")
    public String menuFormLowToHigh(Model model) {
        List<DishDto> dish = menuService.showAllMenuFromLowPriceToHigh();

        model.addAttribute(PARAM_DISH, dish);
        return MENU_PAGE;
    }

    @RequestMapping(value = "/menu/fastFood")
    public String dishesByCategoryFastFood(Model model) {
        String category = "Fast food";
        List<DishDto> dish = menuService.showDishesByCategory(category);

        model.addAttribute(PARAM_DISH, dish);
        return MENU_PAGE;
    }

    @RequestMapping(value = "/menu/healthyFood")
    public String dishesByCategoryHealthyFood(Model model) {
        String category = "Healthy food";
        List<DishDto> dish = menuService.showDishesByCategory(category);

        model.addAttribute(PARAM_DISH, dish);
        return MENU_PAGE;
    }

    @RequestMapping(value = "/menu/desert")
    public String dishesByCategoryDesert(Model model) {
        String category = "Desert";
        List<DishDto> dish = menuService.showDishesByCategory(category);

        model.addAttribute(PARAM_DISH, dish);
        return MENU_PAGE;
    }
}
