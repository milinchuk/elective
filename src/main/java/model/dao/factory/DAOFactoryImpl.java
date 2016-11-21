package model.dao.factory;

import config.connection.AbstractConnection;
import model.dao.impl.CourseDAOImpl;
import model.dao.impl.ProgressDAOImpl;
import model.dao.impl.UserDAOImpl;
import model.dao.interfaces.CourseDAO;
import model.dao.interfaces.ProgressDAO;
import model.dao.interfaces.UserDAO;

import java.sql.Connection;

/**
 * Created by click on 11/5/2016.
 */
public class DAOFactoryImpl implements DAOFactory {

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
            // add exception
            return null;
        }
    }

    @Override
    public UserDAO getUserDAO(AbstractConnection connection){
        if (connection.getConnection() instanceof Connection) {
            return new UserDAOImpl((Connection)connection.getConnection());
        } else {
            // add exception
            return null;
        }
    }

    @Override
    public ProgressDAO getProgressDAO(AbstractConnection connection) {
        if (connection.getConnection() instanceof Connection) {
            return new ProgressDAOImpl((Connection)connection.getConnection());
        } else {
            // add exception
            return null;
        }
    }

}
