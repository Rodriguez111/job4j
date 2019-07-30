package ru.job4j.crudservlet.controller;

import ru.job4j.crudservlet.AdvancedUser;
import ru.job4j.crudservlet.Pages;
import ru.job4j.crudservlet.controller.logic.ValidateService;
import ru.job4j.crudservlet.controller.logic.ValidateServiceWithRoles;
import ru.job4j.crudservlet.controller.logic.ValidatorWithRole;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserMainPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("listOfUsers", ValidateServiceWithRoles.getInstance().findAllAdvUsers());
        req.getRequestDispatcher(Pages.MAIN_JSP.page).forward(req, resp);
    }
}
