package ru.job4j.siteparser.sql;

import ru.job4j.siteparser.utils.PropertiesManager;

import java.io.File;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;

public class SQLConnection {


    public static Connection createConnection() {
        createDBDirectory(new File(PropertiesManager.getDbDirPath()));
        Connection connection = null;
        try {
            Class.forName(PropertiesManager.getSqlDriver());
            String url = PropertiesManager.getDbUrl();
            connection = DriverManager.getConnection(url, "", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    private static void createDBDirectory(File dbDir) {
        if (!Files.exists(dbDir.toPath())) {
            dbDir.mkdirs();
        }
    }

}
