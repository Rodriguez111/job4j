package ru.job4j.trackersql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.tracker.ITracker;
import ru.job4j.tracker.Item;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TrackerSQL implements ITracker, AutoCloseable {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    private final static String TABLE_NAME = "tracker";
    private final static String COLUMN_1 = "id";
    private final static String COLUMN_2 = "name";
    private final static String COLUMN_3 = "description";

    private static final Logger LOG = LoggerFactory.getLogger(TrackerSQL.class);

    public TrackerSQL() {
        init();
        createSQLTable();
    }

    public boolean init() {
        try (InputStream inputStream = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(inputStream);
            Class.forName(config.getProperty("driver-class-name"));
            this.connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );

        } catch (Exception e) {
           throw new IllegalStateException();
        }
        return this.connection != null;
    }

    public void createSQLTable() {
        try {
         preparedStatement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                 + "(" + COLUMN_1 + " serial primary key, " + COLUMN_2 + " VARCHAR(60) NOT NULL, " + COLUMN_3 + " VARCHAR(180) NOT NULL)");

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }


    @Override
    public Item add(Item item) {
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO " + TABLE_NAME + " (" + COLUMN_2 + ", " + COLUMN_3 + ") VALUES "
                    + "(?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, item.getName());
            preparedStatement.setString(2, item.getDescription());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                item.setId(String.valueOf(resultSet.getInt(1)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public boolean replace(String id, Item item) {
        boolean result = false;
        try {

            preparedStatement = connection.prepareStatement("SELECT EXISTS (SELECT " + COLUMN_1 + " FROM " + TABLE_NAME + " WHERE id = ?)");
            preparedStatement.setInt(1, Integer.valueOf(id));
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                if (resultSet.getBoolean(1)) {
                    preparedStatement = connection.prepareStatement("UPDATE " + TABLE_NAME + " SET " + COLUMN_2 + " = ?, " + COLUMN_3 + " = ? "
                            + " WHERE " + COLUMN_1 + " = ?");
                    preparedStatement.setString(1, item.getName());
                    preparedStatement.setString(2, item.getDescription());
                    preparedStatement.setInt(3, Integer.valueOf(id));
                    preparedStatement.executeUpdate();
                    result = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        try {

            preparedStatement = connection.prepareStatement("SELECT EXISTS (SELECT " + COLUMN_1 + " FROM " + TABLE_NAME + " WHERE id = ?)");
            preparedStatement.setInt(1, Integer.valueOf(id));
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                if (resultSet.getBoolean(1)) {
                    preparedStatement = connection.prepareStatement("DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_1 + " = ?");
                    preparedStatement.setInt(1, Integer.valueOf(id));
                    preparedStatement.executeUpdate();
                    result = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Item> findAll() {
        List<Item> itemList = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM Tracker");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Item item = new Item(resultSet.getString("name"), resultSet.getString("description"));
                item.setId(String.valueOf(resultSet.getInt("id")));
                itemList.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemList;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> itemList = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_2 + " = ?");
            preparedStatement.setString(1, key);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Item item = new Item(resultSet.getString("name"), resultSet.getString("description"));
                item.setId(String.valueOf(resultSet.getInt("id")));
                itemList.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemList;
    }

    @Override
    public Item findById(String id) {
        Item item = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_1 + " = ?");
            preparedStatement.setInt(1, Integer.valueOf(id));
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                item = new Item(resultSet.getString("name"), resultSet.getString("description"));
                item.setId(String.valueOf(resultSet.getInt("id")));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public void close() throws Exception {
        connection.close();
        resultSet.close();
        preparedStatement.close();
    }

}
