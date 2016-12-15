package config.connection.factory;

import config.connection.AbstractConnection;
import config.connection.MySqlConnection;
import org.apache.log4j.Logger;
import utils.constants.LoggingMessagesHanldler;
import utils.constants.ResourseNames;

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
            DataSource dataSource = (DataSource) context.lookup(ResourseNames.DATA_SOURCE);
            logger.info(LoggingMessagesHanldler.SUCCESSFUL_CONNECTION);
            return new MySqlConnection(dataSource.getConnection());
        } catch (Exception e) {
            logger.error(LoggingMessagesHanldler.ERROR_CONNECTION);
            throw new RuntimeException(LoggingMessagesHanldler.ERROR_CONNECTION);
        }
    }
}
