package config.connection.factory;

import config.connection.AbstractConnection;

import java.sql.Connection;
import java.sql.Statement;

/**
 * Created by click on 11/8/2016.
 */
public interface ConnectionFactory {
    public AbstractConnection getMySqlConnection();
}
