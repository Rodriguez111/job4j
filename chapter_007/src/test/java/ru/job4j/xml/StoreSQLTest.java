package ru.job4j.xml;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;

import java.sql.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

class StoreSQLTest {
   private Config config = new Config();
   private Connection connection;
   private PreparedStatement preparedStatement;
   private ResultSet resultSet;
   private final static String TABLE_NAME = "entries";
   private final static String COLUMN_1 = "entry";

   @BeforeEach
   public void initBefore() {
       initConnection();
   }

    private void initConnection() {
        try {
            this.connection = DriverManager.getConnection(config.get("url"),
                    config.get("username"), config.get("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void dropTableIfExists() {
        try {
            preparedStatement = connection.prepareStatement("DROP TABLE IF EXISTS " + TABLE_NAME);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void shouldCreateDBWhenNotExists() throws SQLException {
      StoreSQL storeSQL = new StoreSQL();
        dropTableIfExists();
        storeSQL.createTable();
    preparedStatement = connection.prepareStatement("SELECT name FROM sqlite_master WHERE type='table' AND name='" + TABLE_NAME + "'");
    resultSet = preparedStatement.executeQuery();

        String actual = resultSet.getString(1);
        String expected = "entries";
        assertThat(actual, is(expected));
   }

    @Test
    public void shouldCount10RowsWhenInsert10Entries() throws SQLException {
        StoreSQL storeSQL = new StoreSQL();
        storeSQL.generateLessThenMillionEntries(10);
        preparedStatement = connection.prepareStatement("SELECT * FROM " + TABLE_NAME);
        resultSet = preparedStatement.executeQuery();
        int actual = 0;
        while (resultSet.next()) {
            actual++;
        }
        int expected = 10;
        assertThat(actual, is(expected));
    }





}