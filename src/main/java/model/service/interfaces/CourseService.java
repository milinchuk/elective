package model.service.interfaces;

import model.entity.Course;
import model.entity.User;

import java.util.List;

/**
 * Created by click on 11/6/2016.
 */
public interface CourseService {
    void create(Course course, User user);
    void delete(Integer id, User user);
    void update(Course course, User user);
    Course findOne(Integer id);
    List<Course> findByTutor(Integer id);
    List<Course> findByStudent(Integer id);
    Course findByName(String name);
    List<Course> findAll();
}
