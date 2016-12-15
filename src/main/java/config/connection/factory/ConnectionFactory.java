package config.connection.factory;

import config.connection.AbstractConnection;

import java.sql.Connection;
import java.sql.Statement;

/**
 * Abstract factory for connections.
 * Factory retrieve AbstractConnection for some type of storage.
 *
 * @author Anastasia Milinchuk
 * @see config.connection.AbstractConnection
 */
public interface ConnectionFactory {
    /**
     * Retrieve abstract connection for MySql database
     *
     * @return connection for MySql
     */
    public AbstractConnection getMySqlConnection();
}
