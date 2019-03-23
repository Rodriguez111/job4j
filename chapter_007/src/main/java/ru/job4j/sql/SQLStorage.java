package ru.job4j.sql;


import org.slf4j.LoggerFactory;

import org.slf4j.Logger;

import java.sql.*;

public class SQLStorage {
    private static final Logger LOG = LoggerFactory.getLogger(SQLStorage.class);

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/car";
        String username = "postgres";
        String password = "password";
        Connection connection = null;
        try {
            connection =  DriverManager.getConnection(url, username, password);


            insert(connection);
           // select(connection);
           // select(connection);


        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }



    }


    public static void select(Connection connection) {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM ? WHERE car.id in (?, ?, ?) ");
            preparedStatement.setString(1, "car");
            preparedStatement.setInt(2, 1);
            preparedStatement.setInt(3, 3);
            preparedStatement.setInt(4, 4);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(String.format("%s %s", resultSet.getString("manufacturer"), resultSet.getString("model")));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                    resultSet.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
    }

    public static void insert(Connection connection) {
        ResultSet generatedKeys = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO car (manufacturer, model, car_body_id, engine_id, transmission_id) VALUES (?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, "Ferrari");
            preparedStatement.setString(2, "F-50");
            preparedStatement.setInt(3, 2);
            preparedStatement.setInt(4, 3);
            preparedStatement.setInt(5, 3);
            preparedStatement.executeUpdate();
            generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                System.out.println(generatedKeys.getInt(1));
            }

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                    generatedKeys.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
    }

    public static void update(Connection connection) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement("UPDATE car SET model = ? WHERE id = ?;");
            preparedStatement.setString(1, "911");
            preparedStatement.setInt(2, 4);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
    }

    public static void delete(Connection connection) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM car WHERE id = ?;");
           preparedStatement.setInt(1, 11);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
    }
}
