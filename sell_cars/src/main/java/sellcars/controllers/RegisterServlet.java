package sellcars.controllers;

import org.json.JSONObject;
import sellcars.service.UserValidator;
import sellcars.service.ValidateUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class RegisterServlet extends HttpServlet {

      private final static UserValidator USER_VALIDATOR = ValidateUser.getINSTANCE();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader bufferedReader = req.getReader();
        StringBuilder sb = new StringBuilder();
        String read;
        while ((read = bufferedReader.readLine()) != null) {
            sb.append(read);
        }
        String stringFromClient = sb.toString();
        JSONObject jsonObject = new JSONObject(stringFromClient);
        String result = USER_VALIDATOR.addUser(jsonObject);

            JSONObject jsonFromServer  = new JSONObject();
            jsonFromServer.put("messageFromServer", result);
            jsonFromServer.put("login", jsonObject.getString("login"));
            resp.setContentType("text/json");
            resp.setCharacterEncoding("UTF-8");
            PrintWriter writer = resp.getWriter();
            writer.print(jsonFromServer);
            writer.flush();

    }
}
