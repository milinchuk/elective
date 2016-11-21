package model.dao.impl.utils;

import model.entity.Course;
import model.entity.Progress;
import model.entity.User;

import java.sql.ResultSet;

/**
 * Created by click on 11/20/2016.
 */
public class ProgressPickUtil implements PickUtil<Progress> {
    @Override
    public Progress pick(ResultSet resultSet) {
        try {
            Progress progress = new Progress();
            progress.setId(resultSet.getInt("id"));
            progress.setMark(resultSet.getInt("mark"));
            progress.setNote(resultSet.getString("note"));

            UserPickUtil userPickUtil = new UserPickUtil();
            User user = userPickUtil.pick(resultSet);

            CoursePickUtil coursePickUtil = new CoursePickUtil();
            Course course = coursePickUtil.pick(resultSet);

            progress.setCourse(course);
            progress.setStudent(user);

            return progress;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
