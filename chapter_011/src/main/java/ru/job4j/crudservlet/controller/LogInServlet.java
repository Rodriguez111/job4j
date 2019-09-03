package ru.job4j.crudservlet.controller;

import ru.job4j.crudservlet.Pages;
import ru.job4j.crudservlet.controller.logic.ValidateService;
import ru.job4j.crudservlet.controller.logic.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogInServlet extends HttpServlet {
    private static final Validator VALIDATOR = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(Pages.LOGIN_JSP.page).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("password");
        String role = VALIDATOR.isCredential(login, pass);

        if (!role.equals("")) {
            HttpSession session = req.getSession();
            session.setAttribute("login", login);
            session.setAttribute("role", role);
            resp.sendRedirect(req.getContextPath() + Pages.MAIN.page);
            //req.getRequestDispatcher(Pages.MAIN.page).forward(req, resp);
        } else {
            req.setAttribute("errorMessage", "No such user/password registered in the system");
            doGet(req, resp);
        }
    }
}
