package ru.job4j.servlets;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Для запуска запускаес скрипт startup.bat в папке tomcat\bin и в адресной строке вводим адрес проекта
 * http://localhost:8080/chapter_011-2.0/echo
 * Для остановки запускаем скрипт shutdown.bat.
 */
public class EchoServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(EchoServlet.class);

    @Override
    public void init() throws ServletException {
        System.out.println("INIT------------------------------===========");
    }
    //    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/html");
//        PrintWriter writer = new PrintWriter(resp.getOutputStream());
//        writer.append("Hello world");
//        writer.flush();
//    }
}
