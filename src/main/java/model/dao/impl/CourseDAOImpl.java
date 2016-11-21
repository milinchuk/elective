package model.dao.impl;

import model.dao.impl.utils.QueryResource;
import model.dao.interfaces.CourseDAO;
import model.dao.impl.utils.CoursePickUtil;
import model.entity.Course;
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
    private Connection connection;
    private CoursePickUtil coursePickUtil;
    private QueryResource resource;

    private static final String QUERIES_URL = "course_query.properties";


    public CourseDAOImpl(Connection connection) {
        this.connection = connection;
        coursePickUtil = new CoursePickUtil();
        resource = new QueryResource(QUERIES_URL);
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
            return course;
        } catch (Exception e){
            e.printStackTrace();
            return null;
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
            return courses;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
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
            return courses;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void create(Course course) {
        try {
            PreparedStatement statement = connection.prepareStatement(resource.getQuery(QueryResource.CREATE));
            statement.setString(1, course.getCode());
            statement.setString(2, course.getName());
            statement.setString(3, course.getAbout());
            statement.setDate(4, course.getStartDate());
            statement.setDate(5, course.getEndDate());
            statement.setInt(6, course.getTutor().getId());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Course course) {
        try {
            PreparedStatement statement = connection.prepareStatement(resource.getQuery(QueryResource.UPDATE));
            statement.setString(1, course.getCode());
            statement.setString(2, course.getName());
            statement.setString(3, course.getAbout());
            statement.setDate(4, course.getStartDate());
            statement.setDate(5, course.getEndDate());
            statement.setInt(6, course.getTutor().getId());
            statement.setInt(7, course.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            PreparedStatement statement = connection.prepareStatement(resource.getQuery(QueryResource.DELETE));
            statement.setInt(1, id);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
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
            return courses;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
