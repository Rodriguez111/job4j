package ru.job4j.todolist.servlets;

import org.json.JSONObject;
import ru.job4j.todolist.controller.ValidateItems;
import ru.job4j.todolist.controller.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ToDoServlet extends HttpServlet {

    private final static Validator VALIDATOR = ValidateItems.getINSTANCE();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPostdoPost");
        BufferedReader bufferedReader = req.getReader();
        StringBuilder sb = new StringBuilder();
        String read;
        while ((read = bufferedReader.readLine()) != null) {
            sb.append(read);
        }
        String requestFromClient = sb.toString();
        JSONObject jsonToClient = null;
        if (requestFromClient.contains("getAll")) {
            jsonToClient = VALIDATOR.getItems(false);
        }
        if (requestFromClient.contains("getOnlyUndone")) {
            jsonToClient = VALIDATOR.getItems(true);
        }
        if (requestFromClient.contains("addItem")) {
            JSONObject jsonObject = new JSONObject(requestFromClient);
            VALIDATOR.add((String) jsonObject.get("description"));
            jsonToClient = new JSONObject();
            jsonToClient.put("itemAdded", "itemAdded");
        }
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.print(jsonToClient);
        writer.flush();

    }
}
