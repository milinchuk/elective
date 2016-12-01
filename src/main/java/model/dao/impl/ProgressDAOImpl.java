package model.dao.impl;

import utils.pickers.resultSet.ProgressResultSetPicker;
import utils.QueryResource;
import model.dao.interfaces.ProgressDAO;
import model.entity.Progress;
import org.apache.log4j.Logger;
import utils.constants.LoggingMessagesHanldler;

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
    private static final Logger logger = Logger.getLogger(ProgressDAOImpl.class);
    private Connection connection;
    private ProgressResultSetPicker progressPickUtil;
    private QueryResource resource;

    public ProgressDAOImpl(Connection connection) {
        this.connection = connection;
        this.progressPickUtil = new ProgressResultSetPicker();
        this.resource = new QueryResource(QueryResource.PROGRESS_QUERIES_URL);
    }

    @Override
    public void create(Progress progress) {
        try {
            PreparedStatement statement = connection.prepareStatement(resource.getQuery(QueryResource.CREATE));
            statement.setInt(1, progress.getCourse().getId());
            statement.setInt(2, progress.getStudent().getId());
            statement.executeUpdate();
            statement.close();
            logger.info(LoggingMessagesHanldler.SUCCESSFUL_CREATE);
        } catch (SQLException e) {
            logger.error(LoggingMessagesHanldler.ERROR_CREATE, e);
            throw new RuntimeException(LoggingMessagesHanldler.ERROR_CREATE, e);
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            PreparedStatement statement = connection.prepareStatement(resource.getQuery(QueryResource.DELETE));
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
            logger.info(LoggingMessagesHanldler.SUCCESSFUL_DELETE);
        } catch (SQLException e) {
            logger.error(LoggingMessagesHanldler.ERROR_DELETE, e);
            throw new RuntimeException(LoggingMessagesHanldler.ERROR_DELETE, e);
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
            statement.close();
            logger.info(LoggingMessagesHanldler.SUCCESSFUL_UPDATE);
        } catch (SQLException e) {
            logger.error(LoggingMessagesHanldler.ERROR_UPDATE, e);
            throw new RuntimeException(LoggingMessagesHanldler.ERROR_UPDATE, e);
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
            statement.close();
            logger.info(LoggingMessagesHanldler.SUCCESSFUL_FIND_ONE);
            return progress;
        } catch (SQLException e) {
            logger.error(LoggingMessagesHanldler.ERROR_FIND_ONE, e);
            throw new RuntimeException(LoggingMessagesHanldler.ERROR_FIND_ONE, e);
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
            statement.close();
            logger.info(LoggingMessagesHanldler.SUCCESSFUL_FIND_ONE);
            return progress;
        } catch (SQLException e) {
            logger.error(LoggingMessagesHanldler.ERROR_FIND_ONE, e);
            throw new RuntimeException(LoggingMessagesHanldler.ERROR_FIND_ONE, e);
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
            statement.close();
            logger.info(LoggingMessagesHanldler.SUCCESSFUL_FIND_BY_STUDENT);
            return progresses;
        } catch (Exception e){
            logger.error(LoggingMessagesHanldler.ERROR_FIND_BY_STUDENT, e);
            throw new RuntimeException(LoggingMessagesHanldler.ERROR_FIND_BY_STUDENT, e);
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
            statement.close();
            logger.info(LoggingMessagesHanldler.SUCCESSFUL_FIND_BY_COURSE);
            return progresses;
        } catch (Exception e){
            logger.error(LoggingMessagesHanldler.ERROR_FIND_BY_COURSE, e);
            throw new RuntimeException(LoggingMessagesHanldler.ERROR_FIND_BY_COURSE, e);
        }
    }
}
