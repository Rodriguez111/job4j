package ru.job4j.crudservlet.presentation;

import ru.job4j.crudservlet.Pages;
import ru.job4j.crudservlet.User;
import ru.job4j.crudservlet.logic.ValidateService;
import ru.job4j.crudservlet.logic.Validator;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UsersServlet extends HttpServlet {
    private final Validator validateService = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = new PrintWriter(resp.getOutputStream());
        printWriter.append(HTML.generateHtml("Users information", HTML.generateStyle1() + generateUsersList() + generateCreateButtonForm()));
        printWriter.flush();
        System.out.println("doGet класса UsersServlet, Пользователей заведено = " + validateService.findAll().size());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private String generateUsersList() {
        StringBuilder sb = new StringBuilder();
        for (User eachUser : validateService.findAll()) {
            sb.append(generateMainTable(eachUser));
        }
        return sb.toString();
    }

    private String generateButtonsBlock(User user) {
        StringBuilder sb = new StringBuilder();
        sb.append("<table>")
                .append("<tr><td>" + generateUpdateButtonForm(user) + "</td><td>" + generateDeleteButtonForm(user) + "</td></tr>")
                .append("</table>");
        return sb.toString();
    }

    private String generateUpdateButtonForm(User user) {
        StringBuilder sb = new StringBuilder();
        sb.append("<form method='get' action='" + Pages.UPDATE.page + "'>")
                .append(" <input type='hidden' name='id' value='" + user.getId() + "'< />")
                .append(" <input type='hidden' name='name' value='" + user.getName() + "'< />")
                .append(" <input type='hidden' name='login' value='" + user.getLogin() + "'< />")
                .append(" <input type='hidden' name='email' value='" + user.getEmail() + "'< />")
                .append("<input type='submit' value='UPDATE'>")
                .append("</form>");
        return sb.toString();
    }

    private String generateDeleteButtonForm(User user) {
        StringBuilder sb = new StringBuilder();
        sb.append("<form method='get' action='" + Pages.DELETE.page + "'>")
                .append(" <input type='hidden' name='id' value='" + user.getId() + "'< />")
                .append(" <input type='hidden' name='name' value='" + user.getName() + "'< />")
                .append(" <input type='hidden' name='login' value='" + user.getLogin() + "'< />")
                .append(" <input type='hidden' name='email' value='" + user.getEmail() + "'< />")
                .append("<input type='hidden' name='createDate' value='" + user.getCreateDate() + "'< />")
                .append("<input type='submit' value='DELETE'>")
                .append("</form>");
        return sb.toString();
    }

    private String generateCreateButtonForm() {
        StringBuilder sb = new StringBuilder();
        sb.append("<form method='get' action='" + Pages.ADD.page + "'>")
                .append("<input type='submit' value='ADD NEW USER'>")
                .append("</form>");
        return sb.toString();
    }

    private String generateInfoForm(User user) {
        StringBuilder sb = new StringBuilder();
        sb.append("<table>")
                .append("<tr><b>User info:</b></tr>")
                .append("<tr><td><label>Name: </label></td><td><label>" + user.getName() + "</label></td></tr>")
                .append("<tr><td><label>Login: </label></td><td><label>" + user.getLogin() + "</label></td></tr>")
                .append("<tr><td><label>E-mail: </label></td><td><label>" + user.getEmail() + "</label></td></tr>")
                .append("<tr><td><label>Create date: </label></td><td><label>" + user.getCreateDate() + "</label></td></tr>")
                .append("</table>");
        return sb.toString();
    }

    private String generateMainTable(User user) {
        StringBuilder sb = new StringBuilder();
        sb.append("<table>")
                .append("<tr><td>" + HTML.generateBlock(generateInfoForm(user) + generateButtonsBlock(user)) + "</td></tr>")
                .append("<table>");
        return sb.toString();
    }
}
