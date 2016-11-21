package model.dao.interfaces;

import model.entity.Course;

import java.util.List;

/**
 * Created by click on 11/5/2016.
 */
public interface CourseDAO {
    public Course findOne(Integer id);
    public List<Course> findByTutor(Integer id);
    public List<Course> findByStudent(Integer id);
    public void create(Course course);
    public void update(Course course);
    public void delete(Integer id);
    Course findByName();
    List<Course> findAll();
}
