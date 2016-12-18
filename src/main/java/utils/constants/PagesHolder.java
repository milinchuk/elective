package utils.constants;

/**
 * Created by click on 11/21/2016.
 */
public class PagesHolder {
    public static final String PREFIX = "/WEB-INF/view/jsp";
    public static final String AUTH_FOLDER = "/authorization";
    public static final String COURSE_FOLDER = "/course";
    public static final String ERROR_FOLDER = "/error";
    public static final String PROGRESS_FOLDER = "/progress";
    public static final String USER_FOLDER = "/user";

    public static final String FIND_COURSES = PREFIX + COURSE_FOLDER + "/findCourses.jsp";
    public static final String PAGE_NOT_FOUND_JSP = PREFIX + ERROR_FOLDER + "/pageNotFound.jsp";
    public static final String TUTOR_COURSES = PREFIX + COURSE_FOLDER + "/tutorCourses.jsp";
    public static final String STUDENTS = PREFIX + PROGRESS_FOLDER + "/students.jsp";
    public static final String PROFILE = PREFIX + USER_FOLDER +"/profile.jsp";
    public static final String LOGIN = PREFIX + AUTH_FOLDER + "/login.jsp";
    public static final String COURSE = PREFIX + COURSE_FOLDER +  "/editCourse.jsp";
    public static final String STUDENT_COURSES = PREFIX + COURSE_FOLDER +  "/studentCourses.jsp";
    public static final String ADD_COURSE = PREFIX + COURSE_FOLDER + "/addCourse.jsp";
    public static final String SIGNUP = PREFIX + AUTH_FOLDER + "/signup.jsp";
    public static final String ERROR_PAGE = PREFIX + ERROR_FOLDER + "/error.jsp";
}
