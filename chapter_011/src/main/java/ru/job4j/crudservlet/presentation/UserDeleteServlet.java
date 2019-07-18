package ru.job4j.crudservlet.presentation;

import ru.job4j.crudservlet.Pages;
import ru.job4j.crudservlet.logic.ValidateService;
import ru.job4j.crudservlet.logic.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserDeleteServlet extends HttpServlet {
    final Validator validateService = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(Pages.DELETE_JSP.page).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        validateService.delete(req);
        resp.sendRedirect(Pages.MAIN.page);
    }
}
