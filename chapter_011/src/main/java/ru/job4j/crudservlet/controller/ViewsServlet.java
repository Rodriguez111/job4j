package ru.job4j.crudservlet.controller;

import ru.job4j.crudservlet.Pages;
import ru.job4j.crudservlet.sql.Consumer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class ViewsServlet extends HttpServlet {
    Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>> dispatcher = new HashMap<>();

    @Override
    public void init() throws ServletException {
        initDispatcher();
    }

    private void initDispatcher() {
        dispatcher.put("add", (request, response) -> {
            try {
                request.getRequestDispatcher(Pages.ADD_JSP.page).forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        });
        dispatcher.put("update", (request, response) -> {
            try {
                request.getRequestDispatcher(Pages.UPDATE_JSP.page).forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        });
        dispatcher.put("delete", (request, response) -> {
            try {
                request.getRequestDispatcher(Pages.DELETE_JSP.page).forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        });
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        dispatcher.get(req.getParameter("action")).accept(req, resp);

    }
}
