package epam.project.spring.controller;

import epam.project.spring.dto.AppUserDto;
import epam.project.spring.service.user.UserService;
import epam.project.spring.service.user_role.UserRoleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static epam.project.spring.util.Constants.PARAM_LOGIN;
import static epam.project.spring.util.Constants.PARAM_USER;
import static epam.project.spring.util.Page.REGISTRATION_PAGE;

/**
 * @author Aleksandr Ovcharenko
 */
@Controller
@RequestMapping(value = "/sign")
public class SignController {
    @Autowired
    UserService userService;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    static final Logger logger = LogManager.getLogger();

    @GetMapping(value = "/up")
    public String getSignPage() {
        return REGISTRATION_PAGE;
    }

    @PostMapping(value = "/up")
    public String signUp(HttpServletRequest request, HttpSession session, @Valid AppUserDto user, Model model) {

//        user.setRole(Role.USER);

        user.setRole("ADMIN");

        String password = user.getPassword();
        String passHash = passwordEncoder.encode(user.getPassword());
        user.setPassword(passHash);

        if (!userService.createUser(user)) {
            model.addAttribute("error_user", true);
            model.addAttribute(PARAM_LOGIN, user.getUsername());
            return "redirect:/error";
        }

        authWithAuthManager(request, user.getUsername(), password);
        session.setAttribute(PARAM_USER, user);

        logger.info("create user with username = " + user.getUsername());
        return "redirect:/";
    }

    public void authWithAuthManager(HttpServletRequest request, String username, String password) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
        authToken.setDetails(new WebAuthenticationDetails(request));

        Authentication authentication = authenticationManager.authenticate(authToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
