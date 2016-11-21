package model.service;

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
        UserDAO userDAO = daoFactory.getUserDAO(connectionFactory.getMySqlConnection());
        userDAO.create(user);
    }

    @Override
    public void delete(String email) {
        UserDAO userDAO = daoFactory.getUserDAO(connectionFactory.getMySqlConnection());
        userDAO.delete(email);
    }

    @Override
    public void update(User user) {
        UserDAO userDAO = daoFactory.getUserDAO(connectionFactory.getMySqlConnection());
        userDAO.update(user);
    }

    @Override
    public User findOne(String email) {
        UserDAO userDAO = daoFactory.getUserDAO(connectionFactory.getMySqlConnection());
        return userDAO.findOne(email);
    }

    @Override
    public List<User> findByCourse(Integer id) {
        UserDAO userDAO = daoFactory.getUserDAO(connectionFactory.getMySqlConnection());
        return userDAO.findByCourseFollow(id);
    }
}
