package model.dao.interfaces;

import model.entity.Course;

import java.util.List;

/**
 * DAO for Course.
 *
 * @author Anastasia Milinchuk
 *
 * @see model.entity.Course
 * @see model.dao.interfaces.UserDAO
 * @see model.dao.interfaces.ProgressDAO
 * @see model.dao.interfaces.GeneralDAO
 */
public interface CourseDAO extends GeneralDAO<Course, Integer> {
    /**
     * Find one course by id. ID must be unique, that's why
     * method return one course or null.
     *
     * @param id is ID of Course
     * @return course if course with id exist and null on the other case
     */
    Course findOne(Integer id);

    /**
     * Find courses which user with userId is not follows.
     *
     * @param userId is id of user
     * @return list of courses which user is not follows
     */
    List<Course> findUnfollow(Integer userId);

    /**
     * Find list of tutor's courses
     *
     * @param id is ID of tutor (user)
     * @return list of tutor's courses
     */
    List<Course> findByTutor(Integer id);

    /**
     * Find student's courses
     *
     * @param id is student is
     * @return list of student's courses
     */
    List<Course> findByStudent(Integer id);

    /**
     * Find all courses
     *
     * @return list of all courses
     */
    List<Course> findAll();
}
