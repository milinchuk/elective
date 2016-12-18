package config.connection.factory;

import config.connection.AbstractConnection;

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
