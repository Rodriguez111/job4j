package ru.job4j.jsp;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Для запуска запускаес скрипт startup.bat в папке tomcat\bin и в адресной строке вводим адрес проекта
 * http://localhost:8080/chapter_011-2.0/echo
 * Для остановки запускаем скрипт shutdown.bat.
 */
public class TestUserController extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestUserController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", TestUserStorage.getINSTANCE().getUsers());
        req.getRequestDispatcher("/WEB-INF/view/TestUserView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("UTF-8");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        TestUserStorage.getINSTANCE().add(new TestUser(login, email));
        resp.sendRedirect(String.format("%s/", req.getContextPath()));

    }
}
