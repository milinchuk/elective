package model.service.interfaces;

import model.entity.Progress;

import java.util.List;

/**
 * Created by click on 11/10/2016.
 */
public interface ProgressService {
    void create(Progress progress);
    void delete(String courseCode, String userEmail);
    void update(Progress progress);
    Progress findOne(String courseCode, String userEmail);
    List<Progress> findByUser(Integer id);
    List<Progress> findByCourse(Integer id);
}
