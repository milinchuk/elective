package service;

import dao.connection.AbstractConnection;
import dao.connection.factory.ConnectionFactory;
import dao.connection.factory.ConnectionFactoryImpl;
import dao.factory.DAOFactory;
import dao.factory.DAOFactoryImpl;
import dao.interfaces.CourseDAO;
import dao.interfaces.ProgressDAO;
import model.entity.Course;
import model.entity.Progress;
import service.interfaces.CourseService;

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
        }

    }

    @Override
    public void delete(Integer id) {
        try (AbstractConnection connection = connectionFactory.getMySqlConnection()){
            connection.beginTransaction();
            CourseDAO courseDAO = daoFactory.getCourseDAO(connection);
            ProgressDAO progressDAO = daoFactory.getProgressDAO(connection);
            List<Progress> progresses = progressDAO.findByCourse(id);
            if (progresses != null) {
                courseDAO.softDelete(id);
            } else {
                courseDAO.delete(id);
            }
            connection.commit();
        }
    }

    @Override
    public void update(Course course) {
        try (AbstractConnection connection = connectionFactory.getMySqlConnection()){
            CourseDAO courseDAO = daoFactory.getCourseDAO(connection);
            connection.beginTransaction();
            courseDAO.update(course);
            connection.commit();
        }
    }

    @Override
    public Course findOne(Integer id) {
        try (AbstractConnection connection = connectionFactory.getMySqlConnection()) {
            CourseDAO courseDAO = daoFactory.getCourseDAO(connection);
            Course course = courseDAO.findOne(id);
            return course;
        }
    }

    @Override
    public List<Course> findByTutor(Integer id) {
        try (AbstractConnection connection = connectionFactory.getMySqlConnection()) {
            CourseDAO courseDAO = daoFactory.getCourseDAO(connection);
            return courseDAO.findByTutor(id);
        }
    }

    @Override
    public List<Course> findUnfollow(Integer studentId) {
        try (AbstractConnection connection = connectionFactory.getMySqlConnection()) {
            CourseDAO courseDAO = daoFactory.getCourseDAO(connection);
            return courseDAO.findUnfollow(studentId);
        }
    }
}
