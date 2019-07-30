package ru.job4j.crudservlet.controller;

import ru.job4j.crudservlet.Pages;
import ru.job4j.crudservlet.controller.logic.ValidateService;
import ru.job4j.crudservlet.controller.logic.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserDeleteServlet extends HttpServlet {
    final Validator validateService = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("id", req.getParameter("id"));
        req.setAttribute("name", req.getParameter("name"));
        req.setAttribute("login", req.getParameter("login"));
        req.setAttribute("email", req.getParameter("email"));
        req.setAttribute("createDate", req.getParameter("createDate"));
        req.getRequestDispatcher(Pages.DELETE_JSP.page).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        validateService.delete(req);
        resp.sendRedirect(req.getContextPath() + Pages.MAIN.page);
    }
}
