package cinema.persistence.storage;

import cinema.persistence.Consumer;
import cinema.persistence.QueryManager;
import cinema.persistence.SQLManager;
import cinema.persistence.models.Place;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBTicket implements TicketStorage {
    private static final Logger LOG = LoggerFactory.getLogger(DBTicket.class);

    private final static DBTicket INSTANCE = new DBTicket();
    private final static DBHall DB_HALL = DBHall.getINSTANCE();

    private DBTicket() {
    }

    public static DBTicket getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public String createTicket(int row, int place, String name, String surname, String phone, float cost) {
        String result = "OK";
        LOG.info("Enter method");
        Connection connection = SQLManager.getINSTANCE().getConnection();
        try {
            connection.setAutoCommit(false);
            String insertQuery = "INSERT INTO tickets "
                    + "(order_date, place, name, surname, phone, cost, canceled)"
                    + "VALUES "
                    + "(current_timestamp, ?, ?, ?, ?, ?, ?)";
            int placeId = DB_HALL.getPlaceId(row, place);
            List<Object> params = List.of(placeId, name, surname, phone, cost, false);
            QueryManager queryManager = new QueryManager(connection);
            queryManager.runQuery(insertQuery, params, (Consumer<PreparedStatement>) PreparedStatement::executeUpdate);
            DB_HALL.engagePlace(row, place, connection);
            connection.commit();
        } catch (Exception e) {
            try {
                LOG.error(e.getMessage(), e);
                connection.rollback();
                LOG.info("\n=======!Db Exception! Rolling Back Data=======\n " + e.getMessage());
                result = e.getMessage();
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        LOG.info("Exit method");
        return result;
    }

    @Override
    public String annulTicket(int row, int place) {
        String result = "OK";
        LOG.info("Enter method");
        Connection connection = SQLManager.getINSTANCE().getConnection();
        try {
            connection.setAutoCommit(false);
            String updateQuery = "UPDATE tickets "
                    + "SET canceled = true WHERE place=? "
                    + "AND order_date = (SELECT MAX(order_date) FROM tickets "
                    + " GROUP BY place "
                    + "HAVING place=?) AND canceled = false";
            int placeId = DB_HALL.getPlaceId(row, place);
            List<Object> params = List.of(placeId, placeId);
            QueryManager queryManager = new QueryManager(connection);
            queryManager.runQuery(updateQuery, params, (Consumer<PreparedStatement>) PreparedStatement::executeUpdate);
            DB_HALL.releasePlace(row, place, connection);
            connection.commit();
        } catch (Exception e) {
            try {
                LOG.error(e.getMessage(), e);
                connection.rollback();
                LOG.info("\n=======!Db Exception! Rolling Back Data=======\n " + e.getMessage());
                result = e.getMessage();
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        LOG.info("Exit method");
        return result;
    }


}
