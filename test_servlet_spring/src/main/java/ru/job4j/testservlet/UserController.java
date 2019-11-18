package ru.job4j.testservlet;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * Для запуска запускаес скрипт startup.bat в папке tomcat\bin и в адресной строке вводим адрес проекта
 * http://localhost:8080/chapter_011-2.0/echo
 * Для остановки запускаем скрипт shutdown.bat.
 */
@Controller
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

@RequestMapping(value="/users", method = RequestMethod.GET)
    public String showUserView(ModelMap model) {
    model.addAttribute("users", UserStorage.getINSTANCE().getUsers());
    return "User_view";

    }

    @RequestMapping(value="/users", method = RequestMethod.POST)
    protected String addUser(HttpServletRequest req) {
        String id = req.getParameter("id");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        UserStorage.getINSTANCE().add(new User(id, login, password, email));
        return "redirect:" +"/users";

    }
}
