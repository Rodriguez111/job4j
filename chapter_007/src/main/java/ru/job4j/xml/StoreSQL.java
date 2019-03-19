package ru.job4j.xml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.sql.SQLStorage;

import java.io.File;
import java.nio.file.Files;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StoreSQL implements AutoCloseable {

private final Config config = new Config();
private static final Logger LOG = LoggerFactory.getLogger(StoreSQL.class);
private  Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private final static String TABLE_NAME = "entries";
    private final static String COLUMN_1 = "entry";

    public StoreSQL() {
        init();
    }

    private void init() {
        createDBDirectory(new File(config.get("dbpath")));
        initConnection();
        createTable();
    }

    private boolean initConnection() {
    try {
        this.connection = DriverManager.getConnection(config.get("url"),
                config.get("username"), config.get("password"));
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return this.connection != null;
}

    public void createTable() {
        try {
            preparedStatement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                    + "(" + COLUMN_1 + " INTEGER NOT NULL)");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void autocommitOnOff(boolean isOn) {
        try {
            connection.setAutoCommit(isOn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void generateLessThenMillionEntries(int size) {
        LOG.info("Inserting data to SQL table " + size + " entries...");
        autocommitOnOff(false);
        if (entriesExists()) {
            clearTable();
        }
        for (int i = 1; i <= size; i++) {
            try {
                preparedStatement = connection.prepareStatement("INSERT INTO " + TABLE_NAME
                        + " VALUES (?)");
                preparedStatement.setInt(1, i);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                    rollback();
                e.printStackTrace();
            }
        }
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   public void generateMoreThenMillionEntries(int size) {
        if (entriesExists()) {
            clearTable();
        }
        for (int i = 1; i <= size; i++) {
            if ((size - i + 1) / 10 >= 1) {
                 batchInsert(size / 10, i);
                 i = i + (size / 10) - 1;
            } else {
                try {
                    preparedStatement = connection.prepareStatement("INSERT INTO " + TABLE_NAME
                            + " VALUES (?)");
                    preparedStatement.setInt(1, i);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
   }

   private void batchInsert(int batchSize, int from) {
        StringBuilder sb = new StringBuilder();
        int upTo = from + batchSize - 1;
        while (from < upTo) {
            sb.append("(")
                    .append(from++)
                    .append("), ");
        }
       sb.append("(")
               .append(from)
               .append(")");
       try {
           preparedStatement = connection.prepareStatement("INSERT INTO " + TABLE_NAME
                   + " VALUES " + sb.toString());
           preparedStatement.executeUpdate();

       } catch (SQLException e) {
           e.printStackTrace();
       }
   }

    public void selectAll() {
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM " + TABLE_NAME);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(COLUMN_1));
            }
            System.out.println("-------");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Entry> selectAllToList() {
        LOG.info("Collecting data from SQL table...");
        List<Entry> result = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM " + TABLE_NAME);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(new Entry(resultSet.getInt(COLUMN_1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private boolean entriesExists() {
        boolean result = false;
        try {
            preparedStatement = connection.prepareStatement("SELECT EXISTS (SELECT * FROM " + TABLE_NAME + ")");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getBoolean(1)) {
                    result = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    public void clearTable() {
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM " + TABLE_NAME);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createDBDirectory(File dbDir) {
        if (!Files.exists(dbDir.toPath()))  {
            dbDir.mkdirs();
        }
    }

    @Override
    public void close() throws Exception {
        connection.close();
        preparedStatement.close();
    }
}
