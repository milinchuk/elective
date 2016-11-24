package model.dao.interfaces;

import model.entity.User;

import java.util.List;

/**
 * Created by click on 11/5/2016.
 */
public interface UserDAO extends GeneralDAO<User> {
    void delete(String email);
    User findOne(Integer id);
    User findOne(String email);
    List<User> findByCourseFollow(Integer id);
}
