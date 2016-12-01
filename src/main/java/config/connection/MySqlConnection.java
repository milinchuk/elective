package config.connection;

import org.apache.log4j.Logger;
import utils.constants.LoggingMessagesHanldler;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by click on 11/14/2016.
 */
public class MySqlConnection implements AbstractConnection {
    private Connection connection;
    private static final Logger logger = Logger.getLogger(MySqlConnection.class);

    public MySqlConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public void beginTransaction() {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            logger.error(LoggingMessagesHanldler.ERROR_BEGIN_TRANSACTION, e);
            throw new RuntimeException(LoggingMessagesHanldler.ERROR_BEGIN_TRANSACTION, e);
        }
    }

    @Override
    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            logger.error(LoggingMessagesHanldler.ERROR_COMMIT, e);
            throw new RuntimeException(LoggingMessagesHanldler.ERROR_COMMIT, e);
        }
    }

    @Override
    public void commit() {
        try {
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            logger.error(LoggingMessagesHanldler.ERROR_COMMIT, e);
            throw new RuntimeException(LoggingMessagesHanldler.ERROR_COMMIT, e);
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(LoggingMessagesHanldler.ERROR_COMMIT, e);
            throw new RuntimeException(LoggingMessagesHanldler.ERROR_COMMIT, e);
        }
    }
}
