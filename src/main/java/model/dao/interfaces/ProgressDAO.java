package model.dao.interfaces;

import model.entity.Progress;

import java.util.List;

/**
 * DAO for student progress
 *
 * @author Anastasia Milinchuk
 *
 * @see model.entity.Progress
 * @see model.dao.interfaces.CourseDAO
 * @see model.dao.interfaces.UserDAO
 * @see model.dao.interfaces.GeneralDAO
 */
public interface ProgressDAO extends GeneralDAO<Progress, Integer> {
    /**
     * Find one progress by id
     *
     * @param id is id of student's progress
     * @return student's progress
     */
    Progress findOne(Integer id);

    /**
     * Find progress by student and course
     *
     * @param userId is id of student
     * @param courseId is id od course
     * @return progress of student by certain course
     */
    Progress findOne(Integer userId, Integer courseId);

    /**
     * Find all student's progresses by all courses which student follows
     * @param id is is id of student
     * @return progresses of student by all courses which student follows
     */
    List<Progress> findByUser(Integer id);

    /**
     * Find all progresses of students who follow course with this ID
     *
     * @param id is course id
     * @return list of progresses of students who follow course with this ID
     */
    List<Progress> findByCourse(Integer id);
}
