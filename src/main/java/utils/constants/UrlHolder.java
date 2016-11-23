package utils.constants;

/**
 * Created by click on 11/21/2016.
 */
public interface UrlHolder {
    String LOGIN = "/login";
    String SIGNUP = "/signup";

    String SUFFIX = "/";
    String PREFIX = "/user";

    String COURSE = PREFIX + "/course" + SUFFIX;
    String COURSES = PREFIX + "/courses";
    String STUDENTS = PREFIX + "/students";
    String PROFILE =  PREFIX + "/profile";
    String MY_COURSES = PREFIX +"/myCourses";
    String LOGOUT = PREFIX + "/logout";
    String FIND = PREFIX + "/find";
    String COURSE_ADD = COURSE + "add";
    String FOLLOW = PREFIX + "/follow";
    String COURSE_EDIT = COURSE + "edit";
    String STUDENT_EDIT = PREFIX + "/student/edit";
    String UNFOLLOW = PREFIX + "/unfollow";
    String COURSE_DELETE = COURSE + "delete";
}
