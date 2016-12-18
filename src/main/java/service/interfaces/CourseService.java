package service.interfaces;

import model.entity.Course;

import java.util.List;

/**
 * Course service with logic.
 *
 * @author Anastasia Milinchuk
 */
public interface CourseService {
    void create(Course course);
    void delete(Integer id);
    void update(Course course);
    Course findOne(Integer id);
    List<Course> findByTutor(Integer tutorId);
    List<Course> findUnfollow(Integer studentId);
}
