package cinema.controller;

import cinema.persistence.models.Place;
import cinema.persistence.storage.DBHall;
import cinema.persistence.storage.DBTicket;
import cinema.persistence.storage.HallStorage;
import cinema.persistence.storage.TicketStorage;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ValidateTicket implements TicketValidator {
    private static final Logger LOG = LoggerFactory.getLogger(ValidateTicket.class);
    private final TicketStorage ticketStorage = DBTicket.getINSTANCE();
    private final static ValidateTicket INSTANCE = new ValidateTicket();

    private ValidateTicket() {
    }

    public static ValidateTicket getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public String createTicket(JSONObject jsonFromClient) {
        LOG.info("Enter method");
        int row = jsonFromClient.getInt("row");
        int place = jsonFromClient.getInt("place");
        String name = jsonFromClient.getString("name");
        String surname = jsonFromClient.getString("surname");
        String phone = jsonFromClient.getString("phone");
        float cost = jsonFromClient.getFloat("cost");
        LOG.info("Exit method");
        return ticketStorage.createTicket(row, place, name, surname, phone, cost);
    }

    @Override
    public String annulTicket(JSONObject jsonFromClient) {
        LOG.info("Enter method");
        int row = jsonFromClient.getInt("row");
        int place = jsonFromClient.getInt("place");
        LOG.info("Exit method");
        return ticketStorage.annulTicket(row, place);
    }


}
