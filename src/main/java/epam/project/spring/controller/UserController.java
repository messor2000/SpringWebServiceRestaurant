package epam.project.spring.controller;

import epam.project.spring.dto.DishDto;
import epam.project.spring.entity.AppUser;
import epam.project.spring.entity.Purse;
import epam.project.spring.entity.order.Order;
import epam.project.spring.entity.order.Status;
import epam.project.spring.service.order.OrderService;
import epam.project.spring.service.user.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;

import static epam.project.spring.util.Constants.*;
import static epam.project.spring.util.Page.*;

/**
 * @author Aleksandr Ovcharenko
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;

    static final Logger logger = LogManager.getLogger();

    @RequestMapping(value = "/purse")
    public String showPurse(HttpSession session) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            AppUser user = userService.findUserByLogin(username).get();

            Purse purse = userService.showUserPurse(user.toDto());
            session.setAttribute(PARAM_PURSE, purse);

            logger.info("show user purse with id = " + purse.getId());
        }

        return PURSE;
    }


    @PostMapping(value = "/topUpPurse")
    public String topUpPurse(@RequestParam(name = "amount") int amount) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            AppUser user = userService.findUserByLogin(username).get();

            if (amount < 0) {
                return REDIRECT_ERROR_PAGE;
            }

            if (!userService.topUpPurse(amount, user)) {
                return REDIRECT_ERROR_PAGE;
            }

            logger.info("top up purse on amount = " + amount);
        }

        return "redirect:" + PURSE;
    }

    @Transactional
    @RequestMapping(value = "/showOrder")
    public String showOrder(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            AppUser user = userService.findUserByLogin(username).get();

            Order order = createNewOrder(user);

            List<DishDto> dish = orderService.showDishInUserOrder(user);

            model.addAttribute(PARAM_ORDER, order);
            model.addAttribute(PARAM_DISH, dish);
        }
        return ORDER;
    }

    @PostMapping(value = "/putInBucket")
    public String putInOrder(@RequestParam("name") String name) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            AppUser user = userService.findUserByLogin(username).get();

            Order order = orderService.findUserOrder(user);

            orderService.putDishInOrder(name, order.toDto());
            orderService.changeOrderStatus("waiting for pay", order);

            logger.info("user make an order with id = " + user.getId());
        }

        return MENU_PAGE;
    }

    @PostMapping(value = "/pay")
    public String pay() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            AppUser user = userService.findUserByLogin(username).get();

            Purse purse = userService.showUserPurse(user.toDto());
            int price = orderService.totalOrderPrice(user);

            if (purse.getAmount() < price) {
                return REDIRECT_ERROR_PAGE;
            }

            Order order = orderService.findUserOrderByStatus(user, Status.WAITING_FOR_PAY);

            if (order.getStatus().equals(Status.PAYED)) {
                return REDIRECT_ERROR_PAGE;
            }

            userService.pay(price, user);
            orderService.changeOrderStatus(Status.PAYED, order);

            logger.info("paid for order with user_id, amount = " + user.getId());
        }
        return ORDER;
    }

    @PostMapping(value = "/newOrder")
    public String createNewOrder(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            AppUser user = userService.findUserByLogin(username).get();

            Order order = orderService.findUserOrderByStatus(user, Status.WAITING_FOR_PAY);

            if (order == null) {
                order = createNewOrder(user);
            }

            List<DishDto> dish = orderService.showDishInUserOrder(user);

            model.addAttribute(PARAM_ORDER, order);
            model.addAttribute(PARAM_DISH, dish);
        }
        return ORDER;
    }

    private Order createNewOrder(AppUser user) {
        Order order = new Order();

        order.setUser(user);
        order.setStatus("empty");

        if (!orderService.createAnOrder(order.toDto(), user)) {
            order = orderService.findUserOrder(user);
        }

        return order;
    }
}
