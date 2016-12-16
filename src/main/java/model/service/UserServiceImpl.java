package model.service;

import config.connection.AbstractConnection;
import config.connection.factory.ConnectionFactory;
import config.connection.factory.ConnectionFactoryImpl;
import dao.factory.DAOFactory;
import dao.factory.DAOFactoryImpl;
import dao.interfaces.UserDAO;
import model.entity.User;
import model.service.interfaces.UserService;

/**
 * Implementation of UserService
 *
 * @author Anastasia Milinchuk
 * @see model.service.interfaces.UserService
 */
public class UserServiceImpl implements UserService {
    private ConnectionFactory connectionFactory;
    private DAOFactory daoFactory;

    private UserServiceImpl(ConnectionFactory connectionFactory, DAOFactory daoFactory) {
        this.connectionFactory = connectionFactory;
        this.daoFactory = daoFactory;
    }

    private static class LazyHolder{
        private static final UserServiceImpl INSTANCE = new UserServiceImpl(ConnectionFactoryImpl.getInstance(),
                DAOFactoryImpl.getInstance());
    }

    public static UserServiceImpl getInstance(){
        return LazyHolder.INSTANCE;
    }

    @Override
    public void create(User user) {
        try (AbstractConnection connection = connectionFactory.getMySqlConnection()) {
            UserDAO userDAO = daoFactory.getUserDAO(connection);
            if ((user != null) && (userDAO.findOne(user.getEmail()) == null)) {
                connection.beginTransaction();
                userDAO.create(user);
                connection.commit();
            }
        }
    }

    @Override
    public void delete(Integer id) {
        try (AbstractConnection connection = connectionFactory.getMySqlConnection()) {
            connection.beginTransaction();
            UserDAO userDAO = daoFactory.getUserDAO(connection);
            connection.commit();
            userDAO.delete(id);
        }
    }

    @Override
    public void update(User user) {
        try (AbstractConnection connection = connectionFactory.getMySqlConnection()) {
            UserDAO userDAO = daoFactory.getUserDAO(connection);
            connection.beginTransaction();
            userDAO.update(user);
            connection.commit();
        }
    }

    @Override
    public User findOne(Integer id) {
        try(AbstractConnection connection = connectionFactory.getMySqlConnection()) {
            UserDAO userDAO = daoFactory.getUserDAO(connection);
            return userDAO.findOne(id);
        }
    }

    @Override
    public User findOne(String email) {
        try (AbstractConnection connection = connectionFactory.getMySqlConnection()) {
            UserDAO userDAO = daoFactory.getUserDAO(connection);
            return userDAO.findOne(email);
        }
    }

}
