package sellcars.controllers;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sellcars.models.User;

import java.security.Principal;


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
