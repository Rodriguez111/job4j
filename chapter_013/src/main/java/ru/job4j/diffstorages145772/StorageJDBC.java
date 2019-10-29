package ru.job4j.diffstorages145772;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class StorageJDBC implements Storage {
    private final JDBCConnection jdbcConnection;

    @Autowired
    public StorageJDBC(JDBCConnection jdbcConnection) {
        this.jdbcConnection = jdbcConnection;
    }


    @Override
    public String add(User user) {
        String result = "Пользователь с таким логином уже существует.";
        if (!userExists(user)) {
            Connection connection = jdbcConnection.getConnection();
            String login = user.getLogin();
            String surname = user.getSurName();
            String name = user.getName();
            String query = "INSERT INTO users "
                    + "(login, surname, name) VALUES "
                    + "(?, ?, ?)";
            try {
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, login);
                ps.setString(2, surname);
                ps.setString(3, name);
                ps.executeUpdate();
                result = "OK";
            } catch (SQLException e) {
                result = e.getMessage();
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public boolean userExists(User user) {
        boolean result = false;
        Connection connection = jdbcConnection.getConnection();
        String query = "SELECT id FROM users "
                + "WHERE login = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, user.getLogin());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
