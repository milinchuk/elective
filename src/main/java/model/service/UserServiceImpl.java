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
        // check if user has email and password
        // check if email unique
        AbstractConnection connection = connectionFactory.getMySqlConnection();
        UserDAO userDAO = daoFactory.getUserDAO(connection);
        if(userDAO.findOne(user.getId()) != null) {
            userDAO.create(user);
        }
        connection.close();
    }

    @Override
    public void delete(String email) {
        AbstractConnection connection = connectionFactory.getMySqlConnection();
        UserDAO userDAO = daoFactory.getUserDAO(connection);
        userDAO.delete(email);
    }

    @Override
    public void update(User user) {
        AbstractConnection connection = connectionFactory.getMySqlConnection();
        UserDAO userDAO = daoFactory.getUserDAO(connection);
        userDAO.update(user);
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
    public List<User> findByCourse(Integer id) {
        AbstractConnection connection = connectionFactory.getMySqlConnection();
        UserDAO userDAO = daoFactory.getUserDAO(connection);
        List<User> users = userDAO.findByCourseFollow(id);
        connection.close();
        return users;
    }
}
