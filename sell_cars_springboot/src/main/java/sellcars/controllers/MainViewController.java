package sellcars.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class MainViewController {
    @RequestMapping("/login")
    public String showMainPageIfNotAuthorized() {
        return "main_view";
    }

    @RequestMapping("/")
    public String showMainPageIfAuthorized() {
        return "main_view";
    }


}
