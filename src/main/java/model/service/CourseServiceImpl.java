package model.service;

import config.connection.AbstractConnection;
import config.connection.factory.ConnectionFactory;
import config.connection.factory.StandartConnectionFactory;
import model.dao.factory.DAOFactory;
import model.dao.factory.DAOFactoryImpl;
import model.dao.interfaces.CourseDAO;
import model.entity.Course;
import model.entity.User;
import model.service.interfaces.CourseService;

import java.util.List;

/**
 * Created by click on 11/6/2016.
 */
// inject connection Factory and DaoFactory
public class CourseServiceImpl implements CourseService {
    private ConnectionFactory connectionFactory;
    private DAOFactory daoFactory;

    private CourseServiceImpl(ConnectionFactory connectionFactory, DAOFactory daoFactory) {
        this.connectionFactory = connectionFactory;
        this.daoFactory = daoFactory;
    }

    private static class LazyHolder{
        private static final CourseServiceImpl INSTANCE = new CourseServiceImpl(StandartConnectionFactory.getInstance(),
                DAOFactoryImpl.getInstance());
    }

    public static CourseServiceImpl getInstance(){
        return LazyHolder.INSTANCE;
    }

    @Override
    public void create(Course course) {
        AbstractConnection connection = connectionFactory.getMySqlConnection();
        CourseDAO courseDAO = daoFactory.getCourseDAO(connection);
        if(courseDAO.findOne(course.getId()) == null) {
            courseDAO.create(course);
        }
        connection.close();
    }

    @Override
    public void delete(Integer id) {
        AbstractConnection connection = connectionFactory.getMySqlConnection();
        CourseDAO courseDAO = daoFactory.getCourseDAO(connection);
        courseDAO.delete(id);
        connection.close();
    }

    @Override
    public void update(Course course) {
        AbstractConnection connection = connectionFactory.getMySqlConnection();
        CourseDAO courseDAO = daoFactory.getCourseDAO(connection);
        courseDAO.update(course);
        connection.close();
    }

    @Override
    public Course findOne(Integer id) {
        AbstractConnection connection = connectionFactory.getMySqlConnection();
        CourseDAO courseDAO = daoFactory.getCourseDAO(connection);
        Course course =  courseDAO.findOne(id);
        connection.close();
        return course;
    }

    @Override
    public List<Course> findByTutor(Integer id) {
        AbstractConnection connection = connectionFactory.getMySqlConnection();
        CourseDAO courseDAO = daoFactory.getCourseDAO(connection);
        List<Course> courses = courseDAO.findByTutor(id);
        connection.close();
        return courses;
    }

    @Override
    public List<Course> findByStudent(Integer id) {
        AbstractConnection connection = connectionFactory.getMySqlConnection();
        CourseDAO courseDAO = daoFactory.getCourseDAO(connection);
        List<Course> courses = courseDAO.findByStudent(id);
        connection.close();
        return courses;
    }

    @Override
    public List<Course> findUnfollow(Integer studentId) {
        AbstractConnection connection = connectionFactory.getMySqlConnection();
        CourseDAO courseDAO = daoFactory.getCourseDAO(connection);
        List<Course> courses = courseDAO.findUnfollow(studentId);
        connection.close();
        return courses;
    }

    @Override
    public Course findByName(String name) {
        AbstractConnection connection = connectionFactory.getMySqlConnection();
        CourseDAO courseDAO = daoFactory.getCourseDAO(connection);
        Course course = courseDAO.findByName();
        connection.close();
        return course;
    }

    @Override
    public List<Course> findAll() {
        AbstractConnection connection = connectionFactory.getMySqlConnection();
        CourseDAO courseDAO = daoFactory.getCourseDAO(connection);
        List<Course> courses = courseDAO.findAll();
        connection.close();
        return courses;
    }
}
