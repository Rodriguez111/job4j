package sellcars.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String showMainPageIfAuthorized() {
        return "main_view";
    }


    @Override
    public String getErrorPath() {
        return "/error";
    }
}
