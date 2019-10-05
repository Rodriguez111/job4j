package ru.job4j.testsrvlet;

import ru.job4j.testsrvlet.rep.UsersRepository;
import ru.job4j.testsrvlet.rep.UsersRepositoryFake;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SignUpServlet extends HttpServlet {
    //private UsersRepository repository = UsersRepositoryFake.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter writer = resp.getWriter();
        writer.write("Helloo");
    }


}
