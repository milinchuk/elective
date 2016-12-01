package model.service;

import config.connection.AbstractConnection;
import config.connection.factory.ConnectionFactory;
import config.connection.factory.StandartConnectionFactory;
import model.dao.factory.DAOFactory;
import model.dao.factory.DAOFactoryImpl;
import model.dao.interfaces.UserDAO;
import model.entity.User;
import model.service.interfaces.UserService;

import java.util.List;

/**
 * Created by click on 11/6/2016.
 */
public class UserServiceImpl implements UserService {
    private ConnectionFactory connectionFactory;
    private DAOFactory daoFactory;

    private UserServiceImpl(ConnectionFactory connectionFactory, DAOFactory daoFactory) {
        this.connectionFactory = connectionFactory;
        this.daoFactory = daoFactory;
    }

    private static class LazyHolder{
        private static final UserServiceImpl INSTANCE = new UserServiceImpl(StandartConnectionFactory.getInstance(),
                DAOFactoryImpl.getInstance());
    }

    public static UserServiceImpl getInstance(){
        return LazyHolder.INSTANCE;
    }

    @Override
    public void create(User user) {
        AbstractConnection connection = connectionFactory.getMySqlConnection();
        UserDAO userDAO = daoFactory.getUserDAO(connection);

        if((user != null) && (userDAO.findOne(user.getEmail()) == null)) {
            connection.beginTransaction();
            userDAO.create(user);
            connection.commit();
        }

        connection.close();
    }

    @Override
    public void delete(String email) {
        AbstractConnection connection = connectionFactory.getMySqlConnection();
        connection.beginTransaction();
        UserDAO userDAO = daoFactory.getUserDAO(connection);
        connection.commit();
        userDAO.delete(email);
    }

    @Override
    public void update(User user) {
        AbstractConnection connection = connectionFactory.getMySqlConnection();
        UserDAO userDAO = daoFactory.getUserDAO(connection);
        connection.beginTransaction();
        userDAO.update(user);
        connection.commit();
        connection.close();
    }

    @Override
    public User findOne(Integer id) {
        AbstractConnection connection = connectionFactory.getMySqlConnection();
        UserDAO userDAO = daoFactory.getUserDAO(connection);
        User user = userDAO.findOne(id);
        connection.close();
        return user;
    }

    @Override
    public User findOne(String email) {
        AbstractConnection connection = connectionFactory.getMySqlConnection();
        UserDAO userDAO = daoFactory.getUserDAO(connection);
        User user = userDAO.findOne(email);
        connection.close();
        return user;
    }

    @Override
    public List<User> findByCourse(Integer id) {
        AbstractConnection connection = connectionFactory.getMySqlConnection();
        UserDAO userDAO = daoFactory.getUserDAO(connection);
        List<User> users = userDAO.findByCourseFollow(id);
        connection.close();
        return users;
    }
}
