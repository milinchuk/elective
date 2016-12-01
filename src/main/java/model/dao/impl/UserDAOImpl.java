package model.dao.impl;

import model.dao.impl.utils.QueryResource;
import model.dao.interfaces.UserDAO;
import model.dao.impl.utils.UserPickUtil;
import model.entity.User;
import org.apache.log4j.Logger;
import utils.constants.LoggingMessagesHanldler;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by click on 11/5/2016.
 */
public class UserDAOImpl implements UserDAO {
    private static final Logger logger = Logger.getLogger(ProgressDAOImpl.class);
    private Connection connection;
    private UserPickUtil userPickUtil;
    private QueryResource resource;

    public UserDAOImpl(Connection connection) {
        this.connection = connection;
        this.userPickUtil = new UserPickUtil();
        this.resource = new QueryResource(QueryResource.USER_QUERIES_URL);
    }

    @Override
    public User findOne(Integer id) {
        try {
            PreparedStatement statement = connection.prepareStatement(resource.getQuery(QueryResource.FIND_ONE));
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            User user = new User();
            if(resultSet.next()){
                user = userPickUtil.pick(resultSet);
            }
            statement.close();
            logger.info(LoggingMessagesHanldler.SUCCESSFUL_FIND_ONE);
            return user;
        } catch (Exception e){
            logger.error(LoggingMessagesHanldler.ERROR_FIND_ONE, e);
            throw new RuntimeException(LoggingMessagesHanldler.ERROR_FIND_ONE, e);
        }
    }

    @Override
    public User findOne(String email) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    resource.getQuery(QueryResource.FIND_ONE_BY_EMAIL));
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            User user = null;
            if(resultSet.next()){
                user = userPickUtil.pick(resultSet);
            }
            statement.close();
            logger.info(LoggingMessagesHanldler.SUCCESSFUL_FIND_ONE);
            return user;
        } catch (Exception e) {
            logger.error(LoggingMessagesHanldler.ERROR_FIND_ONE, e);
            throw new RuntimeException(LoggingMessagesHanldler.ERROR_FIND_ONE, e);
        }
    }

    @Override
    public List<User> findByCourseFollow(Integer id) {
        try {
            PreparedStatement statement = connection.prepareStatement(resource.getQuery(QueryResource.FIND_BY_COURSE));
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            List<User> users = new ArrayList<User>();
            while (resultSet.next()){
                users.add(userPickUtil.pick(resultSet));
            }
            statement.close();
            logger.info(LoggingMessagesHanldler.SUCCESSFUL_FIND_BY_COURSE);
            return users;
        } catch (SQLException e) {
            logger.error(LoggingMessagesHanldler.ERROR_FIND_BY_COURSE, e);
            throw new RuntimeException(LoggingMessagesHanldler.ERROR_FIND_BY_COURSE, e);
        }
    }

    @Override
    public void create(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(resource.getQuery(QueryResource.CREATE));
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setInt(5, user.getRole());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            logger.info(LoggingMessagesHanldler.SUCCESSFUL_CREATE);
        } catch (Exception e){
            logger.error(LoggingMessagesHanldler.ERROR_CREATE, e);
            throw new RuntimeException(LoggingMessagesHanldler.ERROR_CREATE, e);
        }
    }

    @Override
    public void update(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement(resource.getQuery(QueryResource.UPDATE));
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.setInt(4, user.getId());
            statement.executeUpdate();
            statement.close();
            logger.info(LoggingMessagesHanldler.SUCCESSFUL_UPDATE);
        } catch (SQLException e) {
            logger.error(LoggingMessagesHanldler.ERROR_UPDATE, e);
            throw new RuntimeException(LoggingMessagesHanldler.ERROR_UPDATE, e);
        }
    }

    @Override
    public void delete(String email) {
        try {
            PreparedStatement statement = connection.prepareStatement(resource.getQuery(QueryResource.DELETE));
            statement.execute();
            statement.close();
            logger.info(LoggingMessagesHanldler.SUCCESSFUL_DELETE);
        } catch (SQLException e) {
            logger.error(LoggingMessagesHanldler.ERROR_DELETE, e);
            throw new RuntimeException(LoggingMessagesHanldler.ERROR_DELETE, e);
        }
    }
}
