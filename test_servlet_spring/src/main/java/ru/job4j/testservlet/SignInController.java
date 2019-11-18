package ru.job4j.testservlet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.http.HttpRequest;

@Controller
public class SignInController {


    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    protected String showLoginPage() {
        return "LogIn_view";
    }


    @RequestMapping(value = "/signin", method = RequestMethod.POST, params = {"login", "password"})
    @PostMapping
    protected String login(ModelMap model, @RequestParam(value = "login") String login,
                           @RequestParam(value = "password") String password, HttpServletRequest req, HttpSession session) {
        String result;
        if (UserStorage.getINSTANCE().isCredential(login, password)) {

            synchronized (session) {
                session.setAttribute("login", login);
            }
            result = "redirect:" + "/users";

        } else {
            req.setAttribute("error", "User is not signed up");
            result = "LogIn_view";
        }
        return result;
    }
}
