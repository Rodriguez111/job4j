package ru.job4j.crudservlet.sql;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SQLManager {
    private final static BasicDataSource SOURCE = new BasicDataSource();

    public SQLManager() {
        initConnection();
        prepareDataStructure();
    }

    private static void initConnection() {
        SOURCE.setUrl("jdbc:postgresql://localhost:5432/users");
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
            createRolesTable();
            createCountriesTable();
            createCitiesTable();
            createDefaultCountries();
            createDefaultCities();
            createUsersTable();
            createDefaultRoles();
            createDefaultUser();
        }
    }

    private boolean checkTable() {
        String query = "SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME='users'";
        QueryManager queryManager = new QueryManager(getConnection());
        List<Object> params = List.of();
        return queryManager.runQuery(query, params, ps -> {
            boolean result = false;
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                result = true;
            }
            return result;
        }).orElse(false);
    }

    private void createRolesTable() {
        String createTable = "CREATE TABLE IF NOT EXISTS roles "
                + "(id serial primary key, "
                + "role character varying(60) UNIQUE NOT NULL)";
        QueryManager queryManager = new QueryManager(getConnection());
        List<Object> params = List.of();
        queryManager.runQuery(createTable, params, (Consumer<PreparedStatement>) PreparedStatement::executeQuery);
    }

    private void createUsersTable() {
        String createTable = "CREATE TABLE IF NOT EXISTS users "
                + "(id serial primary key, "
                + "name character varying(20) NOT NULL, "
                + "roleId INTEGER REFERENCES roles (id) NOT NULL,"
                + "login character varying(20) UNIQUE NOT NULL,"
                + "password character varying(16) NOT NULL,"
                + "email character varying(30) NOT NULL,"
                + "countryId INTEGER REFERENCES countries (id) NOT NULL,"
                + "cityId INTEGER REFERENCES cities (id) NOT NULL,"
                + "create_date character varying(19) NOT NULL)";
        QueryManager queryManager = new QueryManager(getConnection());
        List<Object> params = List.of();
        queryManager.runQuery(createTable, params, (Consumer<PreparedStatement>) PreparedStatement::executeQuery);
    }

    private void createCountriesTable() {
        String createTable = "CREATE TABLE IF NOT EXISTS countries "
                + "(id serial primary key, "
                + "country character varying(30) UNIQUE NOT NULL)";
        QueryManager queryManager = new QueryManager(getConnection());
        List<Object> params = List.of();
        queryManager.runQuery(createTable, params, (Consumer<PreparedStatement>) PreparedStatement::executeQuery);
    }


    private void createCitiesTable() {
        String createTable = "CREATE TABLE IF NOT EXISTS cities "
                + "(id serial primary key, "
                + "city character varying(30) NOT NULL, "
                + "countryId INTEGER REFERENCES countries (id) NOT NULL)";
        QueryManager queryManager = new QueryManager(getConnection());
        List<Object> params = List.of();
        queryManager.runQuery(createTable, params, (Consumer<PreparedStatement>) PreparedStatement::executeQuery);
    }


    private void createDefaultRoles() {
        String createTable = "INSERT INTO roles "
                + "(role) "
                + "VALUES ('administrator'), ('user')";
        QueryManager queryManager = new QueryManager(getConnection());
        List<Object> params = List.of();
        queryManager.runQuery(createTable, params, (Consumer<PreparedStatement>) PreparedStatement::executeQuery);
    }

    private void createDefaultUser() {
        Date rawDate = new Date(System.currentTimeMillis());
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.YYYY HH:mm:ss");
        String currentDate = dateFormat.format(rawDate);
        String insertUser = "INSERT INTO users "
                + "(name, roleId, login, password, email, countryId, cityId, create_date) "
                + "VALUES ('root', (SELECT id FROM roles WHERE role = 'administrator'), 'root', 'root', 'admin@mail.com', 1, 1, '" + currentDate + "')";
        QueryManager queryManager = new QueryManager(getConnection());
        List<Object> params = List.of();
        queryManager.runQuery(insertUser, params, (Consumer<PreparedStatement>) PreparedStatement::executeQuery);
    }

    private void createDefaultCountries() {
        String insertUser = "INSERT INTO countries "
                + "(country) "
                + "VALUES ('Argentina'),"
                + "('Australia'),"
                + "('Belarus'),"
                + "('Canada'),"
                + "('Germany'),"
                + "('Poland'),"
                + "('Russia'),"
                + "('Ukraine'),"
                + "('USA')";
        QueryManager queryManager = new QueryManager(getConnection());
        List<Object> params = List.of();
        queryManager.runQuery(insertUser, params, (Consumer<PreparedStatement>) PreparedStatement::executeQuery);
    }

    private void createDefaultCities() {
        String insertUser = "INSERT INTO cities "
                + "(city, countryId) "
                + "VALUES ('Buenos Aires', (SELECT id FROM countries WHERE country = 'Argentina')),"
                + "('Cordoba', (SELECT id FROM countries WHERE country = 'Argentina')),"
                + "('La Plata', (SELECT id FROM countries WHERE country = 'Argentina')),"
                + "('Sydney', (SELECT id FROM countries WHERE country = 'Australia')),"
                + "('Melbourne', (SELECT id FROM countries WHERE country = 'Australia')),"
                + "('Adelaide', (SELECT id FROM countries WHERE country = 'Australia')),"
                + "('Minsk', (SELECT id FROM countries WHERE country = 'Belarus')),"
                + "('Vitebsk', (SELECT id FROM countries WHERE country = 'Belarus')),"
                + "('Mogilev', (SELECT id FROM countries WHERE country = 'Belarus')),"
                + "('Ottawa', (SELECT id FROM countries WHERE country = 'Canada')),"
                + "('Toronto', (SELECT id FROM countries WHERE country = 'Canada')),"
                + "('Victoria', (SELECT id FROM countries WHERE country = 'Canada')),"
                + "('Berlin', (SELECT id FROM countries WHERE country = 'Germany')),"
                + "('Hamburg', (SELECT id FROM countries WHERE country = 'Germany')),"
                + "('Hanover', (SELECT id FROM countries WHERE country = 'Germany')),"
                + "('Warsaw', (SELECT id FROM countries WHERE country = 'Poland')),"
                + "('Krakow', (SELECT id FROM countries WHERE country = 'Poland')),"
                + "('Gdańsk', (SELECT id FROM countries WHERE country = 'Poland')),"
                + "('Moscow', (SELECT id FROM countries WHERE country = 'Russia')),"
                + "('Saint Petersburg', (SELECT id FROM countries WHERE country = 'Russia')),"
                + "('Novosibirsk', (SELECT id FROM countries WHERE country = 'Russia')),"
                + "('Krasnodar', (SELECT id FROM countries WHERE country = 'Russia')),"
                + "('Kyiv', (SELECT id FROM countries WHERE country = 'Ukraine')),"
                + "('Odessa', (SELECT id FROM countries WHERE country = 'Ukraine')),"
                + "('Dnipro', (SELECT id FROM countries WHERE country = 'Ukraine')),"
                + "('Lviv', (SELECT id FROM countries WHERE country = 'Ukraine')),"
                + "('New York', (SELECT id FROM countries WHERE country = 'USA')),"
                + "('Los Angeles', (SELECT id FROM countries WHERE country = 'USA')),"
                + "('Washington', (SELECT id FROM countries WHERE country = 'USA')),"
                + "('Dallas', (SELECT id FROM countries WHERE country = 'USA')),"
                + "('Houston', (SELECT id FROM countries WHERE country = 'USA'))";

        QueryManager queryManager = new QueryManager(getConnection());
        List<Object> params = List.of();
        queryManager.runQuery(insertUser, params, (Consumer<PreparedStatement>) PreparedStatement::executeQuery);
    }
}
