package ru.job4j.trackersql;


import ru.job4j.tracker.ITracker;
import ru.job4j.tracker.Item;
import ru.job4j.trackersql.exeptions.ExHandler;


import java.io.InputStream;
import java.sql.*;
import java.util.*;

public class TrackerSQL implements ITracker, AutoCloseable {
    ExHandler exHandler = new ExHandler();

    private Connection connection;

    private final static String TABLE_NAME = "tracker";
    private final static String COLUMN_1 = "id";
    private final static String COLUMN_2 = "name";
    private final static String COLUMN_3 = "description";

    private QueryHandle queryHandle;

    public TrackerSQL(Connection connection) {
        this.connection = connection;
        createSQLTable();
        this.queryHandle = new QueryHandle(connection);
    }

    public TrackerSQL() {
        init();
        createSQLTable();
        this.queryHandle = new QueryHandle(this.connection);
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
        exHandler.exHandle(() -> {
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                    + "(" + COLUMN_1 + " serial primary key, " + COLUMN_2 + " VARCHAR(60) NOT NULL, " + COLUMN_3 + " VARCHAR(180) NOT NULL)");
            preparedStatement.executeUpdate();
        });
    }

    @Override
    public Item add(Item item) {
        String query = "INSERT INTO " + TABLE_NAME + " (" + COLUMN_2 + ", " + COLUMN_3 + ") VALUES (?, ?)";
        List<Object> listOfParams = List.of(item.getName(), item.getDescription());
        Optional<Item> resultItem =  queryHandle.processQuery(query, listOfParams, ps -> {
            ps.executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                    item.setId(String.valueOf(resultSet.getInt(1)));
                }
            return item;
     }, 1);
        return resultItem.get();
    }

    @Override
    public boolean replace(String id, Item item) {
     String query = "SELECT EXISTS (SELECT " + COLUMN_1 + " FROM " + TABLE_NAME + " WHERE id = ?)";
     List<Object> listOfParams = List.of(Integer.valueOf(id));
        Optional<Boolean> result =  queryHandle.processQuery(query, listOfParams, ps -> {
            ResultSet resultSet =  ps.executeQuery();
            Optional<Boolean> res = Optional.of(false);
            if (resultSet.next()) {
                if (resultSet.getBoolean(1)) {
                    String query2 = "UPDATE " + TABLE_NAME + " SET " + COLUMN_2 + " = ?, " + COLUMN_3 + " = ? WHERE " + COLUMN_1 + " = ?";
                    List<Object> listOfParams2 = List.of(item.getName(), item.getDescription(), Integer.valueOf(id));
                    res = queryHandle.processQuery(query2, listOfParams2, ps2 -> {
                        ps2.executeUpdate();
                        return true;
                    });
                }
            }
            return res.get();
        });
    return result.get();
    }

        @Override
    public boolean delete(String id) {
        String query = "SELECT EXISTS (SELECT " + COLUMN_1 + " FROM " + TABLE_NAME + " WHERE id = ?)";
        List<Object> listOfParams = List.of(Integer.valueOf(id));
            Optional<Boolean> result = queryHandle.processQuery(query, listOfParams, ps -> {
                ResultSet resultSet = ps.executeQuery();
                Optional<Boolean> result2 = Optional.of(false);
                if (resultSet.next()) {
                    if (resultSet.getBoolean(1)) {
                        String query2 = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_1 + " = ?";
                       result2 = queryHandle.processQuery(query2, listOfParams, ps2 -> {
                            ps2.executeUpdate();
                            return true;
                        });
                    }
                }
               return result2.get();
            });
            return result.get();
        }

    @Override
    public List<Item> findAll() {
        String query = "SELECT * FROM Tracker";
        Optional<List<Item>> resultList = queryHandle.processQuery(query, List.of(), ps -> {
            List<Item> itemList = new ArrayList<>();
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Item item = new Item(resultSet.getString("name"), resultSet.getString("description"));
                item.setId(String.valueOf(resultSet.getInt("id")));
                itemList.add(item);
            }
            return itemList;
        });
        return resultList.get();
    }

    @Override
    public List<Item> findByName(String name) {
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_2 + " = ?";
        List<Object> listOfParams = List.of(name);
        Optional<List<Item>> resultList = queryHandle.processQuery(query, listOfParams, ps -> {
            List<Item> itemList = new ArrayList<>();
           ResultSet resultSet = ps.executeQuery();
           while (resultSet.next()) {
               Item item = new Item(resultSet.getString("name"), resultSet.getString("description"));
               item.setId(String.valueOf(resultSet.getInt("id")));
               itemList.add(item);
           }
           return itemList;
        });
        return resultList.get();
    }

    @Override
    public Item findById(String id) {
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_1 + " = ?";
        List<Object> listOfParams = List.of(Integer.valueOf(id));
        Optional<Item> resultItem = queryHandle.processQuery(query, listOfParams, ps -> {
            Item item = null;
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                item = new Item(resultSet.getString("name"), resultSet.getString("description"));
                item.setId(String.valueOf(resultSet.getInt("id")));

            }
            return item;
        });
        Item item = null;
        if (resultItem.isPresent()) {
            item = resultItem.get();
        }
        return item;
    }


    @Override
    public void close() throws Exception {
        connection.close();
    }
}