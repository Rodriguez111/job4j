package cinema.service;

import cinema.controller.HallValidator;
import cinema.controller.ValidateHall;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class HallServlet extends HttpServlet {
    private final static HallValidator HALL_VALIDATOR = ValidateHall.getINSTANCE();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        StringBuilder builder = new StringBuilder();
        String read = "";
        while ((read = reader.readLine()) != null) {
            builder.append(read);
        }
        String requestFromClient = builder.toString();

        JSONObject jsonToClient = null;
        if (requestFromClient.contains("getHallInfo")) {
            jsonToClient = HALL_VALIDATOR.getHallInfo();
        }
        if (requestFromClient.contains("clearHall")) {
            jsonToClient = HALL_VALIDATOR.clearHall();
        }
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.print(jsonToClient);
        writer.flush();
    }
}
