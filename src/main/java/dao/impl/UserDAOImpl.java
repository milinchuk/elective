package dao.impl;

import utils.QueryResource;
import dao.interfaces.UserDAO;
import utils.constants.LoggingMessagesHolder;
import utils.pickers.resultSet.UserResultSetPicker;
import model.entity.User;
import org.apache.log4j.Logger;

import java.sql.*;

/**
 * Implementation of UserDAO which works with MySQL
 *
 * @author Anastasia Milinchuk
 * @see dao.interfaces.ProgressDAO
 * @see model.entity.Progress
 */
public class UserDAOImpl implements UserDAO {
    /**
     * Logger for logging errors
     */
    private static final Logger logger = Logger.getLogger(ProgressDAOImpl.class);

    /**
     * Concrete sql connection
     */
    private Connection connection;

    /**
     * Class for retrieving user from ResultSet
     */
    private UserResultSetPicker userPickUtil;

    /**
     * Class with resources urls for obtaining properties file
     */
    private QueryResource resource;

    public UserDAOImpl(Connection connection) {
        this.connection = connection;
        this.userPickUtil = new UserResultSetPicker();
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
            logger.info(LoggingMessagesHolder.SUCCESSFUL_FIND_ONE);
            return user;
        } catch (Exception e){
            logger.error(LoggingMessagesHolder.ERROR_FIND_ONE, e);
            throw new RuntimeException(LoggingMessagesHolder.ERROR_FIND_ONE, e);
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
            logger.info(LoggingMessagesHolder.SUCCESSFUL_FIND_ONE);
            return user;
        } catch (Exception e) {
            logger.error(LoggingMessagesHolder.ERROR_FIND_ONE, e);
            throw new RuntimeException(LoggingMessagesHolder.ERROR_FIND_ONE, e);
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
            logger.info(LoggingMessagesHolder.SUCCESSFUL_CREATE);
        } catch (Exception e){
            logger.error(LoggingMessagesHolder.ERROR_CREATE, e);
            throw new RuntimeException(LoggingMessagesHolder.ERROR_CREATE, e);
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
            logger.info(LoggingMessagesHolder.SUCCESSFUL_UPDATE);
        } catch (SQLException e) {
            logger.error(LoggingMessagesHolder.ERROR_UPDATE, e);
            throw new RuntimeException(LoggingMessagesHolder.ERROR_UPDATE, e);
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            PreparedStatement statement = connection.prepareStatement(resource.getQuery(QueryResource.DELETE));
            statement.setInt(1, id);
            statement.execute();
            statement.close();
            logger.info(LoggingMessagesHolder.SUCCESSFUL_DELETE);
        } catch (SQLException e) {
            logger.error(LoggingMessagesHolder.ERROR_DELETE, e);
            throw new RuntimeException(LoggingMessagesHolder.ERROR_DELETE, e);
        }
    }
}
