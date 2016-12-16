package dao.interfaces;

import model.entity.User;

/**
 * DAO for User
 *
 * @author Anastasia Milinchuk
 *
 * @see model.entity.User
 * @see dao.interfaces.CourseDAO
 * @see dao.interfaces.ProgressDAO
 * @see dao.interfaces.GeneralDAO
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
