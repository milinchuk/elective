package config.connection;

import org.apache.log4j.Logger;
import utils.constants.LoggingException;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by click on 11/14/2016.
 */
public class MySqlConnection implements AbstractConnection {
    private static final Logger logger = Logger.getLogger(MySqlConnection.class);
    private Connection connection;

    public MySqlConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public void beginTransaction() {
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_NONE);
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            logger.error(LoggingException.ERROR_BEGIN_TRANSACTION, e);
            throw new RuntimeException(LoggingException.ERROR_BEGIN_TRANSACTION, e);
        }
    }

    @Override
    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            logger.error(LoggingException.ERROR_COMMIT, e);
            throw new RuntimeException(LoggingException.ERROR_COMMIT, e);
        }
    }

    @Override
    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            logger.error(LoggingException.ERROR_COMMIT, e);
            throw new RuntimeException(LoggingException.ERROR_COMMIT, e);
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(LoggingException.ERROR_COMMIT, e);
            throw new RuntimeException(LoggingException.ERROR_COMMIT, e);
        }
    }
}
