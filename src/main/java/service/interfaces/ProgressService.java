package service.interfaces;

import model.entity.Progress;

import java.util.List;

/**
 * Progress service
 *
 * @author Anastasia Milinchuk
 */
public interface ProgressService {
    void create(Progress progress);
    void delete(Integer progressId);
    void update(Progress progress);
    Progress findOne(Integer id);
    List<Progress> findByUser(Integer id);
    List<Progress> findByCourse(Integer id);
}
