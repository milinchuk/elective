package model.dao.impl;

import model.dao.impl.utils.QueryResource;
import model.dao.interfaces.CourseDAO;
import model.dao.impl.utils.CoursePickUtil;
import model.entity.Course;
import org.apache.log4j.Logger;
import utils.constants.LoggingMessagesHanldler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by click on 11/5/2016.
 */
public class CourseDAOImpl implements CourseDAO {
    private static final Logger logger = Logger.getLogger(CourseDAOImpl.class);
    private Connection connection;
    private CoursePickUtil coursePickUtil;
    private QueryResource resource;

    public CourseDAOImpl(Connection connection) {
        this.connection = connection;
        coursePickUtil = new CoursePickUtil();
        resource = new QueryResource(QueryResource.COURSE_QUERIES_URL);
    }

    @Override
    public Course findOne(Integer id) {
        try {
            PreparedStatement statement = connection.prepareStatement(resource.getQuery(QueryResource.FIND_ONE));
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            Course course = new Course();
            if(resultSet.next()){
                course = coursePickUtil.pick(resultSet);
            }
            statement.close();
            logger.info(LoggingMessagesHanldler.SUCCESSFUL_FIND_ONE);
            return course;
        } catch (Exception e){
            logger.error(LoggingMessagesHanldler.ERROR_FIND_ONE, e);
            throw new RuntimeException(LoggingMessagesHanldler.ERROR_FIND_ONE, e);
        }
    }

    @Override
    public List<Course> findUnfollow(Integer userId) {
        try {
            List<Course> courses = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(resource.getQuery(QueryResource.FIND_UNFOLLOW));
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                courses.add(coursePickUtil.pick(resultSet));
            }
            statement.close();
            logger.info(LoggingMessagesHanldler.SUCCESSFUL_FIND_UNFOLLOW);
            return courses;
        } catch (SQLException e) {
            logger.error(LoggingMessagesHanldler.ERROR_UNFOLLOW, e);
            throw new RuntimeException(LoggingMessagesHanldler.ERROR_UNFOLLOW, e);
        }
    }

    @Override
    public List<Course> findByTutor(Integer id) {
        List<Course> courses = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(resource.getQuery(QueryResource.FIND_BY_TUTOR));
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                courses.add(coursePickUtil.pick(resultSet));
            }
            statement.close();
            logger.info(LoggingMessagesHanldler.SUCCESSFUL_FIND_BY_TUTOR);
            return courses;
        } catch (SQLException e) {
            logger.error(LoggingMessagesHanldler.ERROR_FIND_BY_TUTOR, e);
            throw new RuntimeException(LoggingMessagesHanldler.ERROR_FIND_BY_TUTOR, e);
        }
    }

    @Override
    public List<Course> findByStudent(Integer id) {
        List<Course> courses = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(resource.getQuery(QueryResource.FIND_BY_STUDENT));
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                courses.add(coursePickUtil.pick(resultSet));
            }
            statement.close();
            logger.info(LoggingMessagesHanldler.SUCCESSFUL_FIND_BY_STUDENT);
            return courses;
        } catch (SQLException e) {
            logger.error(LoggingMessagesHanldler.ERROR_FIND_BY_STUDENT, e);
            throw new RuntimeException(LoggingMessagesHanldler.ERROR_FIND_BY_STUDENT, e);
        }
    }

    @Override
    public void create(Course course) {
        try {
            PreparedStatement statement = connection.prepareStatement(resource.getQuery(QueryResource.CREATE));
            statement.setString(1, course.getName());
            statement.setString(2, course.getAbout());
            statement.setDate(3, course.getStartDate());
            statement.setDate(4, course.getEndDate());
            statement.setInt(5, course.getTutor().getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            logger.error(LoggingMessagesHanldler.ERROR_CREATE, e);
            throw new RuntimeException(LoggingMessagesHanldler.ERROR_CREATE, e);
        }

    }

    @Override
    public void update(Course course) {
        try {
            PreparedStatement statement = connection.prepareStatement(resource.getQuery(QueryResource.UPDATE));
            statement.setString(1, course.getName());
            statement.setString(2, course.getAbout());
            statement.setDate(3, course.getStartDate());
            statement.setDate(4, course.getEndDate());
            statement.setInt(5, course.getTutor().getId());
            statement.setInt(6, course.getId());
            statement.executeUpdate();
            statement.close();
            logger.info(LoggingMessagesHanldler.SUCCESSFUL_UPDATE);
        } catch (SQLException e) {
            logger.error(LoggingMessagesHanldler.ERROR_UPDATE, e);
            throw new RuntimeException(LoggingMessagesHanldler.ERROR_UPDATE, e);
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            PreparedStatement statement = connection.prepareStatement(resource.getQuery(QueryResource.DELETE));
            statement.setInt(1, id);
            statement.execute();
            statement.close();
            logger.info(LoggingMessagesHanldler.SUCCESSFUL_DELETE);
        } catch (SQLException e) {
            logger.error(LoggingMessagesHanldler.ERROR_DELETE, e);
            throw new RuntimeException(LoggingMessagesHanldler.ERROR_DELETE, e);
        }
    }

    @Override
    public Course findByName() {
        return null;
    }

    @Override
    public List<Course> findAll() {
        try {
            PreparedStatement statement = connection.prepareStatement(resource.getQuery(QueryResource.FIND_ALL));
            List<Course> courses = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                courses.add(coursePickUtil.pick(resultSet));
            }
            statement.close();
            logger.info(LoggingMessagesHanldler.SUCCESSFUL_FIND_ALL);
            return courses;
        } catch (SQLException e) {
            logger.error(LoggingMessagesHanldler.ERROR_FIND_ALL, e);
            throw new RuntimeException(LoggingMessagesHanldler.ERROR_FIND_ALL, e);
        }
    }
}
