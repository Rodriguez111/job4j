package ru.job4j.servlets;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.crudservlet.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Для запуска запускаес скрипт startup.bat в папке tomcat\bin и в адресной строке вводим адрес проекта
 * http://localhost:8080/chapter_011-2.0/echo
 * Для остановки запускаем скрипт shutdown.bat.
 */
public class EchoServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(EchoServlet.class);
    private final List<User> users = new CopyOnWriteArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<!DOCTYPE html>"
               + "<html lang=\"en\">"
               + "<head>\n"
               + "    <meta charset=\"UTF-8\">"
               + "    <title>Title</title>"
               + "</head>"
               + "<body>"
               + "<form action='" + req.getContextPath() + "echo' method='post'>"
               + "Name: = <input type='text' name='name'/><br>"
               + "Login: = <input type='text' name='login'/><br>"
               + "E-mail: = <input type='text' name='email'/><br>"
               + "<input type='submit'/>"
               + "</form>"
               + "</body>"
               + "</html>");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String pass = req.getParameter("password");
        String email = req.getParameter("email");
        this.users.add(new User(name, login, pass, email, "no date"));
        doGet(req, resp);

    }
}
