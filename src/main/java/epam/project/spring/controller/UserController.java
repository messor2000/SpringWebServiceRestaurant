package epam.project.spring.controller;

import epam.project.spring.dto.AppUserDto;
import epam.project.spring.dto.DishDto;
import epam.project.spring.dto.OrderDto;
import epam.project.spring.dto.PurseDto;
import epam.project.spring.entity.AppUser;
import epam.project.spring.entity.Order;
import epam.project.spring.entity.Purse;
import epam.project.spring.service.order.OrderService;
import epam.project.spring.service.user.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
                return "redirect:/error";
            }

            if (!userService.topUpPurse(amount, user)) {
                return "redirect:/error";
            }

            logger.info("top up purse on amount = " + amount);
        }

        return PURSE;
    }

    //TODO add ability to watch an order
    @RequestMapping(value = "/showOrder")
    public String showOrder(HttpServletRequest request, HttpSession session, @Valid OrderDto orderDto, Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            AppUser user = userService.findUserByLogin(username).get();
            System.out.println(user.toString());

            orderDto.setUser(user);

            Order order =  orderService.createAnOrder(orderDto);
            System.out.println(order.toString());

            List<DishDto> dish = orderService.showDishInOrder(order.toDto());

            model.addAttribute(PARAM_ORDER, order);
            model.addAttribute(PARAM_DISH, dish);
        }
        return ORDER;
    }

    //TODO add ability to put dish into order
    @PostMapping(value = "/putInBucket")
    public String putInOrder(HttpServletRequest request, HttpSession session, @Valid DishDto dishDto, Model model, @RequestParam("name") String name) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            AppUser user = userService.findUserByLogin(username).get();

            Order order = orderService.findUserOrder(user);

            System.out.println(order.toString());

            orderService.putDishInOrder(name, order.toDto());


            logger.info("user make an order with id = " + user.getId());
        }

        return MENU_PAGE;
    }
//
//
//    @PostMapping(value = "/product")
//    public String product(HttpServletRequest request, @Valid ProductDTO product) {
//        Boolean isPublic = request.getParameter(PARAM_PUBLIC) == null ? false : request.getParameter(PARAM_PUBLIC).equals("on") ? true : false;
//        product.setCommon(isPublic);
//        product.setDeleted(false);
//
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (principal instanceof UserDetails) {
//            String email = ((UserDetails) principal).getUsername();
//            User user = userService.findUserByLogin(email).get();
//            product.setUser(user);
//            productService.createProduct(product);
//            logger.info("create product with id = " + product.getId());
//        }
//
//        String referer = request.getHeader("Referer");
//        return "redirect:" + referer;
//    }


    //TODO add ability to pay
    @PostMapping(value = "/pay")
    public String pay() {
        return MENU_PAGE;
    }

    public void setPurseForUser(PurseDto purseDto, AppUser user) {
        purseDto.setAmount(0);
        purseDto.setUser(user);
    }

}
