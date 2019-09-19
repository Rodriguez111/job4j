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

public class DBHall implements HallStorage {
    private static final Logger LOG = LoggerFactory.getLogger(DBHall.class);

    private final static DBHall INSTANCE = new DBHall();

    private DBHall() {
    }

    public static DBHall getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public void engagePlace(int row, int place, Connection connection) throws Exception {
        String updateHall = "UPDATE hall "
                + "SET engaged = true WHERE row=? AND place=? AND engaged=false";
        QueryManager queryManager = new QueryManager(connection);
        List<Object> params = List.of(row, place);
        queryManager.runQuery(updateHall, params, ps -> {
            int result = ps.executeUpdate();
            if (result == 0) {
                throw new SQLException("Это место уже зарезервировано");
            }
        });
    }

    @Override
    public void releasePlace(int row, int place, Connection connection) throws Exception {
        String updateHall = "UPDATE hall "
                + "SET engaged = false WHERE row=? AND place=? AND engaged=true";
        QueryManager queryManager = new QueryManager(connection);
        List<Object> params = List.of(row, place);
        queryManager.runQuery(updateHall, params, ps -> {
            int result = ps.executeUpdate();
            if (result == 0) {
                throw new SQLException("Это место уже свободно");
            }
        });
    }

    @Override
    public List<Place> getHallInfo() {
        LOG.info("Enter method");
        List<Place> result = new ArrayList<>();
        Connection connection = SQLManager.getINSTANCE().getConnection();
        QueryManager queryManager = new QueryManager(connection);
        String query = "SELECT * FROM hall ORDER BY row, place";
        List<Object> params = new ArrayList<>();
        try {
            queryManager.runQuery(query, params, ps -> {
                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    int row = resultSet.getInt("row");
                    int place = resultSet.getInt("place");
                    boolean engaged = resultSet.getBoolean("engaged");
                    float cost = resultSet.getFloat("cost");
                    Place newPlace = new Place(row, place, engaged, cost);
                    result.add(newPlace);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        LOG.info("Exit method");
        return result;
    }

    public int getPlaceId(int row, int place) {
        LOG.info("Enter method");
        List<Object> params = List.of(row, place);
        Connection connection = SQLManager.getINSTANCE().getConnection();
        QueryManager queryManager = new QueryManager(connection);
        String query = "SELECT id FROM hall WHERE row = ? AND place = ?";
        int result = -1;
        try {
            result = queryManager.runQuery(query, params, ps -> {
                ResultSet resultSet = ps.executeQuery();
                int resultId = -1;
                if (resultSet.next()) {
                    resultId = resultSet.getInt("id");
                }
                return resultId;
            }).get();
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error(e.getMessage(), e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    LOG.error(e.getMessage(), e);
                }
            }
        }
        LOG.info("Exit method");
        return result;
    }

    @Override
    public String clearHall() {
        LOG.info("Enter method");
        String result = "OK";
        Connection connection = SQLManager.getINSTANCE().getConnection();
        QueryManager queryManager = new QueryManager(connection);
        String query = "UPDATE hall SET engaged=false ";
        List<Object> params = List.of();
        try {
            queryManager.runQuery(query, params, (Consumer<PreparedStatement>) PreparedStatement::executeUpdate);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error(e.getMessage(), e);
            result = "Возникла ошибка при очистке зала";
        }
        LOG.info("Exit method");
        return result;
    }
}
