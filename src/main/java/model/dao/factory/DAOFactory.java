package model.dao.factory;

import config.connection.AbstractConnection;
import model.dao.interfaces.CourseDAO;
import model.dao.interfaces.ProgressDAO;
import model.dao.interfaces.UserDAO;

/**
 * Created by click on 11/5/2016.
 */
public interface DAOFactory {
    CourseDAO getCourseDAO(AbstractConnection connection);
    UserDAO getUserDAO(AbstractConnection connection);
    ProgressDAO getProgressDAO(AbstractConnection connection);
}

/*
 * Abstract connection : startTransaction, commit, rollback
 */
