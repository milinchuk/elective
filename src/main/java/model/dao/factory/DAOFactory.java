package model.dao.factory;

import config.connection.AbstractConnection;
import model.dao.interfaces.CourseDAO;
import model.dao.interfaces.ProgressDAO;
import model.dao.interfaces.UserDAO;

/**
 * DAO factory
 *
 * @author Anastasia Milinchuk
 *
 * @see model.dao.interfaces.CourseDAO
 * @see model.dao.interfaces.UserDAO
 * @see model.dao.interfaces.ProgressDAO
 */
public interface DAOFactory {
    /**
     * Create CourseDAO
     *
     * @param connection is abstract connection to storage
     * @return certain course DAO
     */
    CourseDAO getCourseDAO(AbstractConnection connection);

    /**
     * Create UserDAO
     * @param connection is abstract connection to storage
     * @return user DAO
     */
    UserDAO getUserDAO(AbstractConnection connection);

    /**
     * Create ProgressDAO
     * @param connection is abstract connection to storage
     * @return progress DAO
     */
    ProgressDAO getProgressDAO(AbstractConnection connection);
}
