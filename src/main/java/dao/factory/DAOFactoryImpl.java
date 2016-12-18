package dao.factory;

import dao.connection.AbstractConnection;
import dao.impl.CourseDAOImpl;
import dao.impl.ProgressDAOImpl;
import dao.impl.UserDAOImpl;
import dao.interfaces.CourseDAO;
import dao.interfaces.ProgressDAO;
import dao.interfaces.UserDAO;
import org.apache.log4j.Logger;
import utils.constants.LoggingMessagesHolder;

import java.sql.Connection;

/**
 * Implementation of DAOFactory
 *
 * @author Anastasia Milinchuk
 * @see dao.factory.DAOFactory
 */
public class DAOFactoryImpl implements DAOFactory {
    private static final Logger logger = Logger.getLogger(DAOFactoryImpl.class);

    private DAOFactoryImpl(){ }

    private static class LazyHolder {
        private static final DAOFactoryImpl INSTANCE = new DAOFactoryImpl();
    }

    public static DAOFactoryImpl getInstance() {
        return LazyHolder.INSTANCE;
    }

    @Override
    public CourseDAO getCourseDAO(AbstractConnection connection) {
        if(connection.getConnection() instanceof Connection) {
            return new CourseDAOImpl((Connection)connection.getConnection());
        } else {
            logger.error(LoggingMessagesHolder.ERROR_WITH_CREATING_DAO);
            throw new RuntimeException();
        }
    }

    @Override
    public UserDAO getUserDAO(AbstractConnection connection){
        if (connection.getConnection() instanceof Connection) {
            return new UserDAOImpl((Connection)connection.getConnection());
        } else {
            logger.error(LoggingMessagesHolder.ERROR_WITH_CREATING_DAO);
            throw new RuntimeException();
        }
    }

    @Override
    public ProgressDAO getProgressDAO(AbstractConnection connection) {
        if (connection.getConnection() instanceof Connection) {
            return new ProgressDAOImpl((Connection)connection.getConnection());
        } else {
            logger.error(LoggingMessagesHolder.ERROR_WITH_CREATING_DAO);
            throw new RuntimeException();
        }
    }

}
