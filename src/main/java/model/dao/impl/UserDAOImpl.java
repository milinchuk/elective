package model.dao.impl;

import model.dao.impl.utils.QueryResource;
import model.dao.interfaces.UserDAO;
import model.dao.impl.utils.UserPickUtil;
import model.entity.User;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by click on 11/5/2016.
 */
public class UserDAOImpl implements UserDAO {
    private Connection connection;
    private UserPickUtil userPickUtil;
    private Properties queries;

    public UserDAOImpl(Connection connection) {
        this.connection = connection;
        this.userPickUtil = new UserPickUtil();
        this.queries = new Properties();
        try {
            InputStream inputStream = UserDAOImpl.class.getClassLoader().getResourceAsStream(QueryResource.USER_QUERIES_URL);
            queries.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findOne(Integer id) {
        try {
            PreparedStatement statement = connection.prepareStatement(queries.getProperty(QueryResource.FIND_ONE));
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            User user = new User();
            if(resultSet.next()){
                user = userPickUtil.pick(resultSet);
            }
            statement.close();
            return user;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User findOne(String email) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    queries.getProperty(QueryResource.FIND_ONE_BY_EMAIL));
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            User user = null;
            if(resultSet.next()){
                user = userPickUtil.pick(resultSet);
            }
            statement.close();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<User> findByCourseFollow(Integer id) {
        try {
            PreparedStatement statement = connection.prepareStatement(queries.getProperty(QueryResource.FIND_BY_COURSE));
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            List<User> users = new ArrayList<User>();
            while (resultSet.next()){
                users.add(userPickUtil.pick(resultSet));
            }
            statement.close();
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void create(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queries.getProperty(QueryResource.CREATE));
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setInt(5, user.getRole());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement(queries.getProperty(QueryResource.UPDATE));
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.setInt(4, user.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String email) {
        try {
            PreparedStatement statement = connection.prepareStatement(queries.getProperty(QueryResource.DELETE));
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
