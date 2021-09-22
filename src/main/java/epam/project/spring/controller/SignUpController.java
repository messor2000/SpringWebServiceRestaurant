package epam.project.spring.controller;

import epam.project.spring.dto.AppUserDto;
import epam.project.spring.entity.AppUser;
import epam.project.spring.entity.UserRole;
import epam.project.spring.util.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author Aleksandr Ovcharenko
 */
@Controller
public class SignUpController {
//    static final Logger logger = LogManager.getLogger();
//
//    private UserService<UserDto> userService;
//
//    public SignUpController(UserService<UserDto> userService) {
//        this.userService = userService;
//    }
//
//    @GetMapping("/signup")
//    public String getSignUp() {
//        return Page.SIGN_UP_PAGE;
//    }
//
//    @PostMapping("/signup")
//    public String postSignUp(@RequestParam Map<String, String> allParams, Model model) {
//
//        AppUserDto user = new AppUserDto();
//        String username = allParams.get("username");
//        String password = allParams.get("password");
//        String email = allParams.get("email");
//        UserRole role = allParams.get("role");
//
//
//        UserDto userDto = new UserDto();
//        String email = allParams.get("email");
//        String password = allParams.get("password");
//        String firstName = allParams.get("first-name");
//        String lastName = allParams.get("last-name");
//        String phone = allParams.get("phone");
//        String role = allParams.get("role");
//        if (email == null || password == null ||
//                firstName == null || lastName == null ||
//                phone == null) {
//            return SIGN_UP_PAGE;
//        } else {
//            userDto.setEmail(email);
//            userDto.setPassword(password);
//            userDto.setFirstName(firstName);
//            userDto.setLastName(lastName);
//            userDto.setPhone(phone);
//            userDto.setRole(Role.valueOf(role));
//        }
//        boolean isSaved = false;
//        try {
//            isSaved = userService.save(userDto);
//        } catch (SuchUserIsAlreadyExistsException e) {
//            model.addAttribute("isUserExists", true);
//            logger.warn("Such user is already exists", e);
//        } catch (InvalidUserException e) {
//            model.addAttribute("isInvalidData", true);
//            logger.warn("Invalid input data", e);
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//
//        String result;
//        if (isSaved) {
//            result = "redirect:" + LOGIN_PAGE;
//        } else {
//            result = SIGN_UP_FILE;
//        }
//        return result;
//    }
}
