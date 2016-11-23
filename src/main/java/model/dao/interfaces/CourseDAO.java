package model.dao.interfaces;

import model.entity.Course;

import java.util.List;

/**
 * Created by click on 11/5/2016.
 */
public interface CourseDAO extends GeneralDAO<Course> {
    public Course findOne(Integer id);
    public List<Course> findUnfollow(Integer userId);
    public List<Course> findByTutor(Integer id);
    public List<Course> findByStudent(Integer id);
    public void delete(Integer id);
    Course findByName();
    List<Course> findAll();
}
