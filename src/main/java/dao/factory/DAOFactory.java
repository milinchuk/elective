package dao.factory;

import config.connection.AbstractConnection;
import dao.interfaces.CourseDAO;
import dao.interfaces.ProgressDAO;
import dao.interfaces.UserDAO;

/**
 * DAO factory
 *
 * @author Anastasia Milinchuk
 *
 * @see dao.interfaces.CourseDAO
 * @see dao.interfaces.UserDAO
 * @see dao.interfaces.ProgressDAO
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
