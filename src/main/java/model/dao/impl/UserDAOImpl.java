package model.dao.impl;

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

    private static final String QUERIES_URL = "user_query.properties";
    private static final String CREATE = "create";
    private static final String DELETE = "delete";
    private static final String UPDATE = "update";
    private static final String FIND_ONE = "findOne";
    private static final String FIND_BY_COURSE = "findByCourse";

    public UserDAOImpl(Connection connection) {
        this.connection = connection;
        this.userPickUtil = new UserPickUtil();
        this.queries = new Properties();
        try {
            InputStream inputStream = UserDAOImpl.class.getClassLoader().getResourceAsStream(QUERIES_URL);
            queries.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findOne(String email) {
        try {
            PreparedStatement statement = connection.prepareStatement(queries.getProperty(FIND_ONE));
            statement.setString(1, email);
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
    public List<User> findByCourseFollow(Integer id) {
        try {
            PreparedStatement statement = connection.prepareStatement(queries.getProperty(FIND_BY_COURSE));
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
            PreparedStatement preparedStatement = connection.prepareStatement(queries.getProperty(CREATE));
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
            PreparedStatement statement = connection.prepareStatement(queries.getProperty(UPDATE));
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setInt(5, user.getRole());
            statement.setInt(6, user.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String email) {
        try {
            PreparedStatement statement = connection.prepareStatement(queries.getProperty(DELETE));
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
