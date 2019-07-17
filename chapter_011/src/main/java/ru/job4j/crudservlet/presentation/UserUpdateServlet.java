package ru.job4j.crudservlet.presentation;

import ru.job4j.crudservlet.Pages;
import ru.job4j.crudservlet.logic.ValidateService;
import ru.job4j.crudservlet.logic.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserUpdateServlet extends HttpServlet {
    final Validator validateService = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = new PrintWriter(resp.getOutputStream());
        printWriter.append(HTML.generateHtml("Update user", HTML.generateStyle1() + HTML.generateBlock(generateUpdateForm(req))));
        printWriter.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        validateService.update(req);
        resp.sendRedirect(Pages.MAIN.page);
    }

    private String generateUpdateForm(HttpServletRequest req) {
        StringBuilder sb = new StringBuilder();
        sb.append("<form  action='" + Pages.UPDATE.page + "'method='post'>")
                .append("<table>")
                .append("<tr><td><label>Existing name: </label></td><td><label>" + req.getParameter("name") + "</label></td></tr>")
                .append("<tr><td><label>New name: </label></td><td><label><input type='text' name='name'></label></td></tr>")
                .append("<tr><td><label>Existing login: </label></td><td><label>" + req.getParameter("login") + "</label></td></tr>")
                .append("<tr><td><label>New login: </label></td><td><label><input type='text' name='login'></label></td></tr>")
                .append("<tr><td><label>Existing e-mail: </label></td><td><label>" + req.getParameter("email") + "</label></td></tr>")
                .append("<tr><td><label>New e-mail: </label></td><td><label><input type='text' name='email'></label></td></tr>")
                .append("<tr><td></td><td><input type='submit' value='UPDATE'></td></tr>")
                .append("</table>")
                .append(" <input type='hidden' name='id' value='" + req.getParameter("id") + "'< />")
                .append("</form>")
                .append(HTML.generateReturnForm());
        return sb.toString();
    }
}
