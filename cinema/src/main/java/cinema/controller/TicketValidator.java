package cinema.controller;

import org.json.JSONObject;

public interface TicketValidator {
    String createTicket(JSONObject jsonFromClient);

    String annulTicket(JSONObject jsonFromClient);
}
