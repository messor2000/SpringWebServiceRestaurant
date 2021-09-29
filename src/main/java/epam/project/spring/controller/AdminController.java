package epam.project.spring.controller;

import epam.project.spring.dto.DishDto;
import epam.project.spring.dto.OrderDto;
import epam.project.spring.service.dish.MenuService;
import epam.project.spring.service.order.OrderService;
import epam.project.spring.service.user.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

import static epam.project.spring.util.Constants.*;
import static epam.project.spring.util.Page.*;

/**
 * @author Aleksandr Ovcharenko
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    UserService userService;
    @Autowired
    MenuService menuService;
    @Autowired
    OrderService orderService;

    static final Logger logger = LogManager.getLogger();

    //TODO add ability to watch all users
    @GetMapping(value = "/allUsers")
    public String showAllUsers() {
        return MENU_PAGE;
    }

    @GetMapping(value = "/addDish")
    public String addDish() {
        return ADD_DISH;
    }

    @PostMapping(value = "/addDish")
    public String addDish(HttpServletRequest request, @Valid DishDto dish) {
        String dishName = request.getParameter(PARAM_DISH_NAME);
        int price = Integer.parseInt(request.getParameter(PARAM_PRICE));
        String category = request.getParameter(PARAM_CATEGORY);
        int amount = Integer.parseInt(request.getParameter(PARAM_AMOUNT));

        dish.setName(dishName);
        dish.setPrice(price);
        dish.setCategory(category);
        dish.setAmount(amount);

        if (!menuService.addDish(dish)) {
            return "redirect:/error";
        }

        logger.info("add new dish with name: " + dish.getName());

        return ADD_DISH;
    }

    @GetMapping(value = "/showAllOrders")
    public String putInOrder(HttpServletRequest request, ModelMap modelMap) {
        List<OrderDto> orderList = (List<OrderDto>) orderService.showAllOrder();
        PagedListHolder<OrderDto> pagedListHolder = new PagedListHolder<>(orderList);
        int page = ServletRequestUtils.getIntParameter(request, "p", 0);

        pagedListHolder.setPage(page);
        pagedListHolder.setPageSize(3);
        modelMap.put("pagedListHolder", pagedListHolder);

        return ORDERS;
    }
}
