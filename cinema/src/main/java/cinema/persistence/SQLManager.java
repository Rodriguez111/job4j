package cinema.persistence;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SQLManager {
    private final static BasicDataSource SOURCE = new BasicDataSource();
    private final static SQLManager INSTANCE = new SQLManager();


    private SQLManager() {
        initConnection();
        prepareDataStructure();
    }

    public static SQLManager getINSTANCE() {
        return INSTANCE;
    }


    private static void initConnection() {
        SOURCE.setUrl("jdbc:postgresql://localhost:5432/cinema");
        SOURCE.setUsername("postgres");
        SOURCE.setPassword("password");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = SOURCE.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private void prepareDataStructure() {
        if (!checkTable()) {
            createHallTable();
            createTicketsTable();
            setPlacesIntoHall(10, 20);
        }
    }

    private boolean checkTable() {
        String query = "SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME='hall'";
        QueryManager queryManager = new QueryManager(getConnection());
        List<Object> params = List.of();
        return queryManager.runQueryWithHandledException(query, params, ps -> {
            boolean result = false;
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                result = true;
            }
            return result;
        }).orElse(false);
    }

    private void createHallTable() {
        String createTable = "CREATE TABLE IF NOT EXISTS hall "
                + "(id serial primary key, "
                + "row INTEGER NOT NULL, "
                + "place INTEGER NOT NULL, "
                + "cost NUMERIC(6, 2) NOT NULL, "
                + "engaged BOOLEAN NOT NULL)";
        QueryManager queryManager = new QueryManager(getConnection());
        List<Object> params = List.of();
        queryManager.runQueryWithHandledException(createTable, params, (Consumer<PreparedStatement>) PreparedStatement::executeUpdate);
    }

    private void createTicketsTable() {
        String createTable = "CREATE TABLE IF NOT EXISTS tickets "
                + "(id serial primary key, "
                + "order_date timestamp with time zone NOT NULL,"
                + "place INTEGER REFERENCES hall (id) NOT NULL,"
                + "name character varying(20) NOT NULL,"
                + "surname character varying(20) NOT NULL,"
                + "phone character varying(13) NOT NULL,"
                + "cost NUMERIC(6, 2) NOT NULL,"
                + "canceled BOOLEAN NOT NULL)";
        QueryManager queryManager = new QueryManager(getConnection());
        List<Object> params = List.of();
        queryManager.runQueryWithHandledException(createTable, params, (Consumer<PreparedStatement>) PreparedStatement::executeUpdate);
    }

    private void setPlacesIntoHall(int rows, int places) {
        String createTable = "INSERT INTO hall"
                + "(row, place, engaged, cost)"
                + "VALUES"
                + composePlacesQuery(rows, places);
        QueryManager queryManager = new QueryManager(getConnection());
        List<Object> params = List.of();
        queryManager.runQueryWithHandledException(createTable, params, (Consumer<PreparedStatement>) PreparedStatement::executeUpdate);
    }

    private String composePlacesQuery(int rows, int places) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= places; j++) {
                sb.append("(" + i + ", " + j + ", " + false + ", " + 120.00 + "), ");
            }
        }
        sb.setLength(sb.length() - 2);
        return sb.toString();
    }
}
