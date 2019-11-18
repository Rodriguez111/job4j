package sellcars.servlets;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sellcars.controller.UserValidator;
import sellcars.controller.ValidateUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Controller
public class LoginController {

    private final static UserValidator USER_VALIDATOR = ValidateUser.getINSTANCE();

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showMainPage() {
        return "main_view";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    protected String loginUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        JSONObject result = USER_VALIDATOR.authorizeUser(login, password);
        if (result.has("userName")) {
            HttpSession session = req.getSession();
            session.setAttribute("userLogin", login);
            session.setAttribute("userName", result.getString("userName"));
            session.setAttribute("userSurname", result.getString("userSurname"));
        } else if (result.has("errorLogin")) {
            req.setAttribute("errorMessage", result.getString("errorLogin"));
        } else if (result.has("errorPassword")) {
            req.setAttribute("errorMessage", result.getString("errorPassword"));
        }
        return "main_view";
    }




}
