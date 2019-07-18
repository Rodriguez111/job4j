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

public class UserAddServlet extends HttpServlet {

   private final Validator validateService = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = new PrintWriter(resp.getOutputStream());
        printWriter.append(generateHtml());
        printWriter.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        validateService.add(req);
        resp.sendRedirect(Pages.MAIN.page);
    }

    private String generateHtml() {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>")
                .append("<head>")
                .append("<h2> Creating new user </h2>")
                .append("</head>")
                .append("<body>")
                .append(HTML.generateStyle1())
                .append(HTML.generateBlock(generateCreateForm()))
                .append("</body>")
                .append("</html>");
        return sb.toString();
    }

        private String generateCreateForm() {
        StringBuilder sb = new StringBuilder();
        sb.append("<form  action='" + Pages.ADD.page + "'method='post'>")
                .append("<table>")
                .append("<tr><td><label>Name: </label></td><td><label><input type=\"text\" name=\"name\"></label></td></tr>")
                .append("<tr><td><label>Login: </label></td><td><label><input type=\"text\" name=\"login\"></label></td></tr>")
                .append("<tr><td><label>E-mail: </label></td><td><label><input type=\"text\" name=\"email\"></label></td></tr>")
                .append("<tr><td></td><td><input type='submit' value='ADD USER'></td></tr>")
                .append("</table>")
                .append("</form>")
                .append(HTML.generateReturnForm());
        return sb.toString();
    }
}
