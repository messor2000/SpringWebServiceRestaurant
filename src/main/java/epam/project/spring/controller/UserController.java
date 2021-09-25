package epam.project.spring.controller;

import epam.project.spring.dto.AppUserDto;
import epam.project.spring.dto.DishDto;
import epam.project.spring.dto.PurseDto;
import epam.project.spring.entity.AppUser;
import epam.project.spring.entity.Purse;
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

import static epam.project.spring.util.Constants.PARAM_LOGIN;
import static epam.project.spring.util.Constants.PARAM_USER;
import static epam.project.spring.util.Page.*;

/**
 * @author Aleksandr Ovcharenko
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserService userService;

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

        AppUserDto userDto = (AppUserDto) session.getAttribute(PARAM_USER);

        PurseDto purse = userService.checkPurseAmount(userDto);
        model.addAttribute("purse", purse);

        return PURSE;
    }


    //TODO add ability to top up a purse
    @PostMapping(value = "/topUpPurse")
    public String topUpPurse() {
        return PURSE;
    }

    //TODO add ability to watch an order
    @RequestMapping(value = "/showOrder")
    public String showOrder() {
        return ORDER;
    }

    //TODO add ability to put dish into order
    @PostMapping(value = "/putInBucket")
    public String putInOrder() {
        return MENU_PAGE;
    }


    //TODO add ability to pay
    @PostMapping(value = "/pay")
    public String pay() {
        return MENU_PAGE;
    }


}
