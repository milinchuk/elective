package dao.connection.factory;

import dao.connection.AbstractConnection;
import dao.connection.MySqlConnection;
import org.apache.log4j.Logger;
import utils.constants.LoggingMessagesHolder;
import utils.constants.ResourceNames;

import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * Connection factory implementation
 *
 * @author Anastasia Milinchuk
 */
public class ConnectionFactoryImpl implements ConnectionFactory {
    private static final Logger logger = Logger.getLogger(ConnectionFactoryImpl.class);

    private ConnectionFactoryImpl() {
    }

    private static class LazyHolder {
        private static final ConnectionFactoryImpl INSTANCE = new ConnectionFactoryImpl();
    }

    public static ConnectionFactoryImpl getInstance(){
        return LazyHolder.INSTANCE;
    }

    public AbstractConnection getMySqlConnection() {
        try {
            InitialContext context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup(ResourceNames.DATA_SOURCE);
            return new MySqlConnection(dataSource.getConnection());
        } catch (Exception e) {
            logger.error(LoggingMessagesHolder.ERROR_CONNECTION);
            throw new RuntimeException(LoggingMessagesHolder.ERROR_CONNECTION);
        }
    }
}
