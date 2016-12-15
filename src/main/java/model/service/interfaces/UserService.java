package model.service.interfaces;

import model.entity.User;

/**
 * User service.
 *
 * @author Anastasia Milinchuk
 */
public interface UserService {
    void create(User user);
    void delete(Integer id);
    void update(User user);
    User findOne(Integer id);
    User findOne(String email);
}
