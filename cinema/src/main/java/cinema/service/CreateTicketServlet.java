package cinema.service;

import cinema.controller.HallValidator;
import cinema.controller.TicketValidator;
import cinema.controller.ValidateHall;
import cinema.controller.ValidateTicket;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class CreateTicketServlet extends HttpServlet {
    private final static TicketValidator TICKET_VALIDATOR = ValidateTicket.getINSTANCE();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        int row = Integer.valueOf(req.getParameter("row"));
        int place = Integer.valueOf(req.getParameter("place"));
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String phone = req.getParameter("phone");
        float cost = Float.valueOf(req.getParameter("cost"));

        JSONObject jsonFromClient = new JSONObject();
        jsonFromClient.put("row", row);
        jsonFromClient.put("place", place);
        jsonFromClient.put("name", name);
        jsonFromClient.put("surname", surname);
        jsonFromClient.put("phone", phone);
        jsonFromClient.put("cost", cost);
        String result = TICKET_VALIDATOR.createTicket(jsonFromClient);
        if (result.equals("OK")) {
            req.setAttribute("serverAnswer", result);
            resp.sendRedirect(req.getContextPath());
        } else {
            req.setAttribute("serverAnswer", result);
            req.getRequestDispatcher("/ticket.jsp").forward(req, resp);
        }
    }
}
