package model.dao.interfaces;

import model.entity.User;

import java.util.List;

/**
 * DAO for User
 *
 * @author Anastasia Milinchuk
 *
 * @see model.entity.User
 * @see model.dao.interfaces.CourseDAO
 * @see model.dao.interfaces.ProgressDAO
 * @see model.dao.interfaces.GeneralDAO
 */
public interface UserDAO extends GeneralDAO<User, Integer> {
    /**
     * Find one user by id.
     *
     * @param id is user id
     * @return user
     */
    User findOne(Integer id);

    /**
     * Find one user by email.
     *
     * @param email is user's email
     * @return user
     */
    User findOne(String email);
}
