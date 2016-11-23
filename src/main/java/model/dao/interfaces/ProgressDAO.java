package model.dao.interfaces;

import model.entity.Progress;

import java.util.List;

/**
 * Created by click on 11/10/2016.
 */
public interface ProgressDAO extends GeneralDAO<Progress> {
    void delete(Integer id);
    Progress findOne(Integer id);
    List<Progress> findByUser(Integer id);
    List<Progress> findByCourse(Integer id);
}
