package cinema.persistence.storage;

import cinema.persistence.models.Place;
import org.json.JSONObject;

import java.util.List;

public interface TicketStorage {
    String createTicket(int row, int place, String name, String surname, String phone, float cost);

    String annulTicket(int row, int place);

}
