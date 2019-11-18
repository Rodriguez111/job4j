package sellcars.servlets;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sellcars.controller.UserValidator;
import sellcars.controller.ValidateUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
@Controller
public class RegisterController {

      private final static UserValidator USER_VALIDATOR = ValidateUser.getINSTANCE();

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    protected void registerUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
