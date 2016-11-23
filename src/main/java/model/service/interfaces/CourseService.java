package model.service.interfaces;

import model.entity.Course;
import model.entity.User;

import java.util.List;

/**
 * Created by click on 11/6/2016.
 */
public interface CourseService {
    void create(Course course);
    void delete(Integer id);
    void update(Course course);
    Course findOne(Integer id);
    Course findByName(String name);
    List<Course> findByTutor(Integer tutorId);
    List<Course> findByStudent(Integer studentId);
    List<Course> findUnfollow(Integer studentId);
    List<Course> findAll();
}
