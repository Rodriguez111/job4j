package ru.job4j.siteparser.sql;


import ru.job4j.siteparser.Vacancy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class SQLManager {
    private final Connection connection;
    private final static String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS vacancy "
            + "(id  INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "name VARCHAR(260) NOT NULL, "
            + "text TEXT NOT NULL, "
            + "link TEXT NOT NULL, "
            + " author VARCHAR(120) NOT NULL, "
            + "date VARCHAR(16) NOT NULL)";

    private final static String SELECT_ID = "SELECT id FROM vacancy"
            + " WHERE name = ?";

    private final static String INSERT = "INSERT INTO vacancy "
            + "(name, text, link, author, date)"
            + "VALUES (?, ?, ?, ?, ?)";

    private final static String SELECT_ALL = "SELECT * FROM vacancy";

    public SQLManager(Connection connection) {
        this.connection = connection;
        createTable();
    }

    private void createTable() {
        QueryManager queryManager = new QueryManager(connection);
        queryManager.executeQuery(CREATE_TABLE, PreparedStatement::execute);
    }

    private boolean entryExists(String name) {
        boolean result;
        List<Object> params = List.of(name);
        QueryManager queryManager = new QueryManager(connection);
        result = queryManager.executeQuery(SELECT_ID, params, ps -> {
            ResultSet resultSet = ps.executeQuery();
            return resultSet.next();

        }).orElse(false);
        return result;
    }

    private boolean addEntry(Vacancy vacancy) {
        boolean result = false;
        if (!entryExists(vacancy.getName())) {
            List<Object> params = List.of(vacancy.getName(), vacancy.getText(), vacancy.getLink(), vacancy.getAuthor(), vacancy.getDate());
            QueryManager queryManager = new QueryManager(connection);
            result = queryManager.executeQuery(INSERT, params, ps -> {
                boolean success = false;
                ps.executeUpdate();
                ResultSet resultSet = ps.getGeneratedKeys();
                if (resultSet.next()) {
                    success = true;
                }
                return success;
            }).orElse(false);
        }
        return result;
    }

    public void addEntryList(List<Vacancy> list) {
        for (Vacancy eachVacancy : list) {
            addEntry(eachVacancy);
        }
    }

    public List<Vacancy> selectAll() {
        List<Vacancy> resultList = new ArrayList<>();
        QueryManager queryManager = new QueryManager(connection);
        queryManager.executeQuery(SELECT_ALL, ps -> {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Vacancy vacancy = new Vacancy(resultSet.getString("name"), resultSet.getString("text"),
                        resultSet.getString("link"), resultSet.getString("author"), resultSet.getString("date"));
                vacancy.setId(resultSet.getInt("id"));
                resultList.add(vacancy);
            }
        });
        return resultList;
    }
}