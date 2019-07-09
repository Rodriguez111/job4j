package ru.job4j.crudservlet.presentation;

import ru.job4j.crudservlet.User;
import ru.job4j.crudservlet.logic.ValidateService;
import ru.job4j.crudservlet.logic.Validator;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class UserServlet extends HttpServlet {
    private final Validator validateService = ValidateService.getInstance();
    private final Map<String, BiFunction<Validator, HttpServletRequest, Boolean>> operations = new HashMap<>();

    @Override
    public void init() throws ServletException {
        System.out.println("Грузим конструктор");
        operations.put("add", Validator::add);
        operations.put("update", Validator::update);
        operations.put("delete", Validator::delete);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = new PrintWriter(resp.getOutputStream());
        printWriter.append(generateHtml());
        printWriter.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("action");
        operations.get(param).apply(this.validateService, req);
        doGet(req, resp);
    }


    private String generateHtml() {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>")
                .append("<head>")
                .append("<h1> Users </h1>")
                .append("</head>")
                .append("<body>")
                .append("<h2> Users information </h2><br>")
                .append(generateUsersList())
                .append("</body>")
                .append("</html>");
        return sb.toString();
    }

    private String generateUsersList() {
        StringBuilder sb = new StringBuilder();
        for (User eachUser : validateService.findAll()) {
            sb.append("ID: " + eachUser.getId() + "<br>")
                    .append("Name: " + eachUser.getName() + "<br>")
                    .append("Login: " + eachUser.getLogin() + "<br>")
                    .append("E-mail: " + eachUser.getEmail() + "<br>")
                    .append("Account created: " + eachUser.getCreateDate() + "<br>")
                    .append("<hr />");
        }
        return sb.toString();
    }
}
