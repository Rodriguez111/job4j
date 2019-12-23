package sellcars.controllers;

import org.json.JSONObject;
import sellcars.service.UserValidator;
import sellcars.service.ValidateUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private final static UserValidator USER_VALIDATOR = ValidateUser.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/sellcars/view/main.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        JSONObject result = USER_VALIDATOR.authorizeUser(login, password);
        if (result.has("userName")) {
            HttpSession session = req.getSession();
            session.setAttribute("userLogin", login);
            session.setAttribute("userName", result.getString("userName"));
            session.setAttribute("userSurname", result.getString("userSurname"));
            resp.sendRedirect(req.getContextPath());
        } else if (result.has("errorLogin")) {
            req.setAttribute("errorMessage", result.getString("errorLogin"));
            req.getRequestDispatcher("/WEB-INF/sellcars/view/main.jsp").forward(req, resp);
        } else if (result.has("errorPassword")) {
            req.setAttribute("errorMessage", result.getString("errorPassword"));
            req.getRequestDispatcher("/WEB-INF/sellcars/view/main.jsp").forward(req, resp);
        }
    }
}
