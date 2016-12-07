package entity;

import model.entity.Course;
import model.entity.User;

/**
 * Created by click on 12/1/2016.
 */
public enum CoursesEnum {
    COURSE_FULL(2, "Course Full", "About", Users.TUTOR.user),
    COURSE_FOR_ADD(1, "Course First", "About Course First", Users.TUTOR.user),
    COURSE_EMPTY(null, null, null, null);

    public final Course course;
    CoursesEnum(Integer id, String name, String about, User tutor) {
        course = new Course();
        course.setId(id);
        course.setName(name);
        course.setAbout(about);
        course.setTutor(tutor);
    }

}
