package config.connection.factory;

import config.connection.AbstractConnection;
import config.connection.MySqlConnection;
import org.apache.log4j.Logger;
import utils.constants.LoggingMessagesHanldler;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;

/**
 * Created by click on 11/8/2016.
 */
public class StandartConnectionFactory implements ConnectionFactory {
    public static final String RESOURCE_NAME = "java:comp/env/jdbc/elective";
    private static final Logger logger = Logger.getLogger(StandartConnectionFactory.class);

    private StandartConnectionFactory() {
    }

    private static class LazyHolder {
        private static final StandartConnectionFactory INSTANCE = new StandartConnectionFactory();
    }

    public static StandartConnectionFactory getInstance(){
        return LazyHolder.INSTANCE;
    }

    public AbstractConnection getMySqlConnection() {
        try {
            InitialContext context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup(RESOURCE_NAME);
            logger.info(LoggingMessagesHanldler.SUCCESSFUL_CONNECTION);
            return new MySqlConnection(dataSource.getConnection());
        } catch (Exception e) {
            logger.error(LoggingMessagesHanldler.ERROR_CONNECTION);
            throw new RuntimeException(LoggingMessagesHanldler.ERROR_CONNECTION);
        }
    }
}
