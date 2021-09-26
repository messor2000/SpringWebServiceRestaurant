package epam.project.spring.controller;

import epam.project.spring.dto.AppUserDto;
import epam.project.spring.dto.DishDto;
import epam.project.spring.dto.OrderDto;
import epam.project.spring.dto.PurseDto;
import epam.project.spring.entity.AppUser;
import epam.project.spring.entity.Purse;
import epam.project.spring.service.order.OrderService;
import epam.project.spring.service.user.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

//    @GetMapping(value = "/purse")
//    public String getSignPage() {
//        return PURSE;
//    }

    @RequestMapping(value = "/purse")
    public String showPurse(HttpServletRequest request, HttpSession session, Model model) {
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

//        if (principal instanceof UserDetails) {
//            String username = ((UserDetails) principal).getUsername();
//            AppUser user = userService.findUserByLogin(username).get();
//
//            Purse purse = null;
//            purse.setUser(user);
//            meals.setUser(user);
//            buildMeals(request, meals);
//            mealsService.createMeals(meals);
//            checkCaloriesLimit(user);
//            logger.info("add meals with id = " + meals.getId());
//        }

//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        if (principal instanceof UserDetails) {
//            Purse purse = userService.createPurse();
//        }
//        Purse purse = userService.createPurse();
//
////        AppUserDto userDto = (AppUserDto) session.getAttribute(PARAM_USER);
//
//        userService.setPurseForUser(userDto);
//
//        PurseDto purse = userService.checkPurseAmount(userDto);
//        model.addAttribute("purse", purse);

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


    //TODO add ability to top up a purse
    @PostMapping(value = "/topUpPurse")
    public String topUpPurse(HttpSession session) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            AppUser user = userService.findUserByLogin(username).get();

            int amount = (int) session.getAttribute(PARAM_AMOUNT);

            userService.topUpPurse(amount, user.toDto());

            logger.info("top up purse on amount = " + amount);
        }

        return PURSE;
    }

    //TODO add ability to watch an order
    @RequestMapping(value = "/showOrder")
    public String showOrder(HttpServletRequest request, HttpSession session, @Valid DishDto dishDto, @Valid OrderDto orderDto) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            AppUser user = userService.findUserByLogin(username).get();

            orderDto.setUser(user);
            orderService.createAnOrder(orderDto);
            session.setAttribute(PARAM_ORDER, orderDto);
        }
        return ORDER;
    }

    //TODO add ability to put dish into order
//    @PostMapping(value = "/putInBucket")
//    public String putInOrder(HttpServletRequest request, @Valid DishDto dishDto, @Valid OrderDto orderDto) {
//
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (principal instanceof UserDetails) {
//            String username = ((UserDetails) principal).getUsername();
//            AppUser user = userService.findUserByLogin(username).get();
//            product.setUser(user);
//            productService.createProduct(product);
//            logger.info("create product with id = " + product.getId());
//        }
//
//        return MENU_PAGE;
//    }
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
