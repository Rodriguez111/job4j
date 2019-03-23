package ru.job4j.trackersql;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Connection, which rollback all commits.
 * It is used for integration test.
 */
public class ConnectionRollback {
    /**
     * Create connection with autocommit=false mode and rollback call, when conneciton is closed.
     * @param connection connection.
     * @return Connection object.
     * @throws SQLException possible exception.
     */

    public static Connection create(Connection connection) throws SQLException {
        connection.setAutoCommit(false);
return null;

    }
}
