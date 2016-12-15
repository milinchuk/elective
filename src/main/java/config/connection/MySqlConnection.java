package config.connection;

import org.apache.log4j.Logger;
import utils.constants.LoggingMessagesHanldler;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * This concrete implementation of {@link config.connection.AbstractConnection}
 * for MySql database. Object contains concrete sql connection {@link java.sql.Connection}
 *
 * @author Anastasia Milichuk
 *
 * @see config.connection.AbstractConnection
 * @see java.sql.Connection
 */
public class MySqlConnection implements AbstractConnection  {
    /**
     * Concrete sql connection. This object get connection to database.
     */
    private Connection connection;

    /**
     * Logger for logging operations and exception.
     */
    private static final Logger logger = Logger.getLogger(MySqlConnection.class);

    /**
     * Constructor which initialize connection.
     *
     * @param connection is connection for MySql database.
     */
    public MySqlConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    /**
     * Method with settings for transaction
     */
    @Override
    public void beginTransaction() {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            logger.error(LoggingMessagesHanldler.ERROR_BEGIN_TRANSACTION, e);
            throw new RuntimeException(LoggingMessagesHanldler.ERROR_BEGIN_TRANSACTION, e);
        }
    }

    /**
     * For rollback in case of problems.
     */
    @Override
    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            logger.error(LoggingMessagesHanldler.ERROR_COMMIT, e);
            throw new RuntimeException(LoggingMessagesHanldler.ERROR_COMMIT, e);
        }
    }

    /**
     * Commit changes.
     */
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

    /**
     * Close connection.
     */
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
