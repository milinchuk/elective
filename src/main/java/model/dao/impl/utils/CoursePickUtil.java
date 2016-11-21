package model.dao.impl.utils;

import model.entity.Course;
import model.entity.User;

import java.sql.ResultSet;

/**
 * Created by click on 11/5/2016.
 */
public class CoursePickUtil implements PickUtil{
    public Course pick(ResultSet resultSet){
        try {
            Course course = new Course();
            course.setId(resultSet.getInt("id"));
            course.setCode(resultSet.getString("code"));
            course.setAbout(resultSet.getString("about"));
            course.setName(resultSet.getString("name"));
            course.setStartDate(resultSet.getDate("start_date"));
            course.setEndDate(resultSet.getDate("end_date"));
            UserPickUtil userPickUtil = new UserPickUtil();
            User user = userPickUtil.pick(resultSet);
            course.setTutor(user);
            return course;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
