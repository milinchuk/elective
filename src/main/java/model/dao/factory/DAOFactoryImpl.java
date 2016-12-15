package model.dao.factory;

import config.connection.AbstractConnection;
import model.dao.impl.CourseDAOImpl;
import model.dao.impl.ProgressDAOImpl;
import model.dao.impl.UserDAOImpl;
import model.dao.interfaces.CourseDAO;
import model.dao.interfaces.ProgressDAO;
import model.dao.interfaces.UserDAO;
import org.apache.log4j.Logger;
import utils.constants.LoggingMessagesHanldler;

import java.sql.Connection;

/**
 * Implementation of DAOFactory
 *
 * @author Anastasia Milinchuk
 * @see model.dao.factory.DAOFactory
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
            logger.error(LoggingMessagesHanldler.ERROR_WITH_CREATING_DAO);
            throw new RuntimeException();
        }
    }

    @Override
    public UserDAO getUserDAO(AbstractConnection connection){
        if (connection.getConnection() instanceof Connection) {
            return new UserDAOImpl((Connection)connection.getConnection());
        } else {
            logger.error(LoggingMessagesHanldler.ERROR_WITH_CREATING_DAO);
            throw new RuntimeException();
        }
    }

    @Override
    public ProgressDAO getProgressDAO(AbstractConnection connection) {
        if (connection.getConnection() instanceof Connection) {
            return new ProgressDAOImpl((Connection)connection.getConnection());
        } else {
            logger.error(LoggingMessagesHanldler.ERROR_WITH_CREATING_DAO);
            throw new RuntimeException();
        }
    }

}
