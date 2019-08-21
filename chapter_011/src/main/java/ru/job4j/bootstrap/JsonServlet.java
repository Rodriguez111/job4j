package ru.job4j.bootstrap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class JsonServlet extends HttpServlet {

   private static final Validator USER_VALIDATOR = UserValidator.getINSTANCE();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        StringBuilder builder = new StringBuilder();
        String read = "";
        while ((read = reader.readLine()) != null) {
            builder.append(read);
        }
        String requestFromClient = builder.toString();
        if (!requestFromClient.equals("justShow")) {
            USER_VALIDATOR.addUser(requestFromClient);
        }
        String jsonString = USER_VALIDATOR.getAllUsers();
        resp.setContentType("text/json");
        PrintWriter writer = resp.getWriter();
        writer.print(jsonString);
        writer.flush();
    }
}
