package model.service;

import config.connection.AbstractConnection;
import config.connection.factory.ConnectionFactory;
import config.connection.factory.ConnectionFactoryImpl;
import model.dao.factory.DAOFactory;
import model.dao.factory.DAOFactoryImpl;
import model.dao.interfaces.CourseDAO;
import model.entity.Course;
import model.service.interfaces.CourseService;

import java.util.List;

/**
 * Implementation of course service
 *
 * @author Anastasia Milinchuk
 */
public class CourseServiceImpl implements CourseService {
    /**
     * Factory that generate new connections
     */
    private ConnectionFactory connectionFactory;

    /**
     * Factory that generate DAOs
     */
    private DAOFactory daoFactory;

    private CourseServiceImpl(ConnectionFactory connectionFactory, DAOFactory daoFactory) {
        this.connectionFactory = connectionFactory;
        this.daoFactory = daoFactory;
    }

    private static class LazyHolder{
        private static final CourseServiceImpl INSTANCE = new CourseServiceImpl(ConnectionFactoryImpl.getInstance(),
                DAOFactoryImpl.getInstance());
    }

    public static CourseServiceImpl getInstance(){
        return LazyHolder.INSTANCE;
    }

    @Override
    public void create(Course course) {
        try (AbstractConnection connection = connectionFactory.getMySqlConnection()){
            CourseDAO courseDAO = daoFactory.getCourseDAO(connection);
            connection.beginTransaction();
            courseDAO.create(course);
            connection.commit();
            connection.close();
        }

    }

    @Override
    public void delete(Integer id) {
        try (AbstractConnection connection = connectionFactory.getMySqlConnection()){
            CourseDAO courseDAO = daoFactory.getCourseDAO(connection);
            connection.beginTransaction();
            courseDAO.delete(id);
            connection.commit();
            connection.close();
        }
    }

    @Override
    public void update(Course course) {
        try (AbstractConnection connection = connectionFactory.getMySqlConnection()){
            CourseDAO courseDAO = daoFactory.getCourseDAO(connection);
            connection.beginTransaction();
            courseDAO.update(course);
            connection.commit();
            connection.close();
        }
    }

    @Override
    public Course findOne(Integer id) {
        try (AbstractConnection connection = connectionFactory.getMySqlConnection()) {
            CourseDAO courseDAO = daoFactory.getCourseDAO(connection);
            Course course = courseDAO.findOne(id);
            connection.close();
            return course;
        }
    }

    @Override
    public List<Course> findByTutor(Integer id) {
        try (AbstractConnection connection = connectionFactory.getMySqlConnection()) {
            CourseDAO courseDAO = daoFactory.getCourseDAO(connection);
            List<Course> courses = courseDAO.findByTutor(id);
            connection.close();
            return courses;
        }
    }

    @Override
    public List<Course> findUnfollow(Integer studentId) {
        try (AbstractConnection connection = connectionFactory.getMySqlConnection()) {
            CourseDAO courseDAO = daoFactory.getCourseDAO(connection);
            List<Course> courses = courseDAO.findUnfollow(studentId);
            connection.close();
            return courses;
        }
    }
}
