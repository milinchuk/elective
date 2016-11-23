package model.service.interfaces;

import model.entity.Progress;

import javax.swing.*;
import java.util.List;

/**
 * Created by click on 11/10/2016.
 */
public interface ProgressService {
    void create(Progress progress);
    void delete(Integer progressId);
    void update(Progress progress);
    Progress findOne(Integer id);
    List<Progress> findByUser(Integer id);
    List<Progress> findByCourse(Integer id);
}
