package epam.project.spring.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Aleksandr Ovcharenko
 */
@Controller
public class CustomErrorController implements ErrorController {

    private static final Logger logger = LogManager.getLogger();

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            logger.info(new StringBuilder().append("Error: error code ").append(statusCode).toString());
        }

        return "error";
    }

    public String getErrorPath() {
        return "/error";
    }
}
