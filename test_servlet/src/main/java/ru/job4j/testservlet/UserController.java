package ru.job4j.testservlet;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * Для запуска запускаес скрипт startup.bat в папке tomcat\bin и в адресной строке вводим адрес проекта
 * http://localhost:8080/chapter_011-2.0/echo
 * Для остановки запускаем скрипт shutdown.bat.
 */
public class UserController extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

                req.setAttribute("users", UserStorage.getINSTANCE().getUsers());
                req.getRequestDispatcher("/WEB-INF/ru/job4j/testservlet/view/User_view.jsp").forward(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        UserStorage.getINSTANCE().add(new User(id, login, password, email));
        resp.sendRedirect(String.format("%s/users", req.getContextPath()));

    }
}
