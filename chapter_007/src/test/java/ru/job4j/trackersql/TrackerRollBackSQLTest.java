package ru.job4j.trackersql;

import org.junit.jupiter.api.Test;
import ru.job4j.tracker.Item;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

class TrackerRollBackSQLTest {


    public Connection init() {
        Connection connection;
        try (InputStream inputStream = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(inputStream);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );

        } catch (Exception e) {
            throw new IllegalStateException();
        }
        return connection;
    }


    @Test
    public void whenAddItemThenReturn1Item() throws SQLException {
        TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()));
            tracker.add(new Item("name", "desc"));
            assertThat(tracker.findByName("name").size(), is(1));
    }

    @Test
    public void whenReplaceItemThenReturnNewItem() throws SQLException {
        TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()));
            tracker.add(new Item("name", "desc"));
          String id = tracker.findByName("name").get(0).getId();
          Item newItem = new Item("New name", "New desc");
          tracker.replace(id, newItem);

          String actual = tracker.findByName("New name").get(0).getName();
          assertThat(actual, is("New name"));
    }

    @Test
    public void whenDeleteItemThenReturnSizeEquals0() throws SQLException {
        TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()));
        tracker.add(new Item("name", "desc"));
        String id = tracker.findByName("name").get(0).getId();

        tracker.delete(id);
        int actual = tracker.findByName("name").size();
        assertThat(actual, is(0));
    }



}