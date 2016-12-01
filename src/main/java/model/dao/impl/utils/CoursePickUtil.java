package model.dao.impl.utils;

import model.entity.Course;
import model.entity.User;

import java.sql.ResultSet;

/**
 * Created by click on 11/5/2016.
 */
public class CoursePickUtil implements PickUtil{
    public static final String COURSES = "courses.";
    public static final String ID = "id";
    public static final String ABOUT = "about";
    public static final String NAME = "name";
    public static final String START_DATE = "start_date";
    public static final String END_DATE = "end_date";

    public Course pick(ResultSet resultSet){
        try {
            Course course = new Course();
            course.setId(resultSet.getInt(COURSES + ID));
            course.setAbout(resultSet.getString(ABOUT));
            course.setName(resultSet.getString(NAME));
            course.setStartDate(resultSet.getDate(START_DATE));
            course.setEndDate(resultSet.getDate(END_DATE));
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
