package ru.job4j.crudservlet.controller;

import ru.job4j.crudservlet.Pages;
import ru.job4j.crudservlet.controller.logic.ValidateService;
import ru.job4j.crudservlet.controller.logic.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserAddServlet extends HttpServlet {

    private final Validator validateService = ValidateService.getInstance();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        System.out.println("UserAddServlet");
        validateService.add(req);
        resp.sendRedirect(req.getContextPath() + Pages.MAIN.page);
    }
}
