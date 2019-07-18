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

public class UserDeleteServlet extends HttpServlet {
    final Validator validateService = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = new PrintWriter(resp.getOutputStream());
        printWriter.append(HTML.generateHtml("Delete user", HTML.generateStyle1() + HTML.generateBlock(generateDeleteForm(req))));
        printWriter.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        validateService.delete(req);
        resp.sendRedirect(Pages.MAIN.page);
    }

    private String generateDeleteForm(HttpServletRequest req) {
        StringBuilder sb = new StringBuilder();
        sb.append("<form  action='" + Pages.DELETE.page + "'method='post'>")
                .append("<b>Are you sure you want to delete this user?</b>")
                .append("<table>")
                .append("<tr><td><label>Name: </label></td><td><label>" + req.getParameter("name") + "</label></td></tr>")
                .append("<tr><td><label>Login: </label></td><td><label>" + req.getParameter("login") + "</label></td></tr>")
                .append("<tr><td><label>E-mail: </label></td><td><label>" + req.getParameter("email") + "</label></td></tr>")
                .append("<tr><td><label>Create date: </label></td><td><label>" + req.getParameter("createDate") + "</label></td></tr>")
                .append("<tr><td></td><td><input type='submit' value='DELETE'></td></tr>")
                .append("</table>")
                .append(" <input type='hidden' name='id' value='" + req.getParameter("id") + "'< />")
                .append("</form>")
                .append(HTML.generateReturnForm());
        return sb.toString();
    }
}
