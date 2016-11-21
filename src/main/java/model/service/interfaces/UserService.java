package model.service.interfaces;

import model.entity.Progress;
import model.entity.User;

import java.util.List;

/**
 * Created by click on 11/6/2016.
 */
public interface UserService {
    void create(User user);
    void delete(String email);
    void update(User user);
    User findOne(String email);
    List<User> findByCourse(Integer id);
}
