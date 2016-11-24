package model.dao.impl;

import config.connection.AbstractConnection;
import config.connection.factory.ConnectionFactory;
import model.dao.impl.utils.ProgressPickUtil;
import model.dao.impl.utils.QueryResource;
import model.dao.interfaces.ProgressDAO;
import model.entity.Course;
import model.entity.Progress;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by click on 11/10/2016.
 */
public class ProgressDAOImpl implements ProgressDAO {
    private Connection connection;
    private ProgressPickUtil progressPickUtil;
    private QueryResource resource;

    public ProgressDAOImpl(Connection connection) {
        this.connection = connection;
        this.progressPickUtil = new ProgressPickUtil();
        this.resource = new QueryResource(QueryResource.PROGRESS_QUERIES_URL);
    }

    @Override
    public void create(Progress progress) {
        try {
            PreparedStatement statement = connection.prepareStatement(resource.getQuery(QueryResource.CREATE));
            statement.setInt(1, progress.getCourse().getId());
            statement.setInt(2, progress.getStudent().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            PreparedStatement statement = connection.prepareStatement(resource.getQuery(QueryResource.DELETE));
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Progress progress) {
        try {
            PreparedStatement statement = connection.prepareStatement(resource.getQuery(QueryResource.UPDATE));
            statement.setString(1, progress.getMark());
            statement.setString(2, progress.getNote());
            statement.setInt(3, progress.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Progress findOne(Integer id) {
        try {
            PreparedStatement statement = connection.prepareStatement(resource.getQuery(QueryResource.FIND_ONE));
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            Progress progress = null;
            if(resultSet.next()){
                progress = progressPickUtil.pick(resultSet);
            }
            return progress;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Progress findOne(Integer userId, Integer courseId) {
        try {
            PreparedStatement statement = connection.prepareStatement(resource.getQuery(QueryResource.FIND_BY_USER_COURSE));
            statement.setInt(1, userId);
            statement.setInt(2, courseId);
            ResultSet resultSet = statement.executeQuery();
            Progress progress = null;
            if(resultSet.next()){
                progress = progressPickUtil.pick(resultSet);
            }
            return progress;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Progress> findByUser(Integer id) {
        try {
            PreparedStatement statement = connection.prepareStatement(resource.getQuery(QueryResource.FIND_BY_STUDENT));
            statement.setInt(1, id);
            List<Progress> progresses = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                progresses.add(progressPickUtil.pick(resultSet));
            }
            return progresses;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Progress> findByCourse(Integer id) {
        try {
            PreparedStatement statement = connection.prepareStatement(resource.getQuery(QueryResource.FIND_BY_COURSE));
            statement.setInt(1, id);
            List<Progress> progresses = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                progresses.add(progressPickUtil.pick(resultSet));
            }
            return progresses;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
