package sellcars.servlets;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sellcars.controller.UserValidator;
import sellcars.controller.ValidateUser;


@Controller
public class LoginController {

    private final static UserValidator USER_VALIDATOR = ValidateUser.getINSTANCE();

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String showMainPage() {
        return "main";
    }



//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getRequestDispatcher("/WEB-INF/sellcars/view/main.jsp").forward(req, resp);
//    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String login = req.getParameter("login");
//        String password = req.getParameter("password");
//        JSONObject result = USER_VALIDATOR.authorizeUser(login, password);
//        if (result.has("userName")) {
//            HttpSession session = req.getSession();
//            session.setAttribute("userLogin", login);
//            session.setAttribute("userName", result.getString("userName"));
//            session.setAttribute("userSurname", result.getString("userSurname"));
//            resp.sendRedirect(req.getContextPath());
//        } else if (result.has("errorLogin")) {
//            req.setAttribute("errorMessage", result.getString("errorLogin"));
//            req.getRequestDispatcher("/WEB-INF/sellcars/view/main.jsp").forward(req, resp);
//        } else if (result.has("errorPassword")) {
//            req.setAttribute("errorMessage", result.getString("errorPassword"));
//            req.getRequestDispatcher("/WEB-INF/sellcars/view/main.jsp").forward(req, resp);
//        }
//    }
}
