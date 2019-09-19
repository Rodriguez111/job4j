package cinema.service;

import cinema.controller.TicketValidator;
import cinema.controller.ValidateTicket;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CancelTicketServlet extends HttpServlet {
    private final static TicketValidator TICKET_VALIDATOR = ValidateTicket.getINSTANCE();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        int row = Integer.valueOf(req.getParameter("row"));
        int place = Integer.valueOf(req.getParameter("place"));

        JSONObject jsonFromClient = new JSONObject();
        jsonFromClient.put("row", row);
        jsonFromClient.put("place", place);
        String result = TICKET_VALIDATOR.annulTicket(jsonFromClient);
        if (result.equals("OK")) {
            req.setAttribute("serverAnswer", result);
            resp.sendRedirect(req.getContextPath());
        } else {
            req.setAttribute("serverAnswer", result);
            req.getRequestDispatcher(req.getContextPath()).forward(req, resp);
        }
    }
}
