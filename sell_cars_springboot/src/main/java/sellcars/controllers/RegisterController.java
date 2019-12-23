package sellcars.controllers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sellcars.service.UserValidator;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class RegisterController {

    private UserValidator userValidator;

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
        String result = userValidator.addUser(jsonObject);
        JSONObject jsonFromServer = new JSONObject();
        jsonFromServer.put("messageFromServer", result);
        jsonFromServer.put("login", jsonObject.getString("login"));
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.print(jsonFromServer);
        writer.flush();
    }

    @RequestMapping(value = "/show_register", method = RequestMethod.GET)
    protected String showRegisterForm() {
        return "register";
    }

    @Autowired
    public void setUserValidator(UserValidator userValidator) {
        this.userValidator = userValidator;
    }
}
