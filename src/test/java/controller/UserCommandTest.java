package controller;

import controller.commands.course.UserCoursesCommand;
import model.entity.Course;
import model.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import service.CourseServiceImpl;
import utils.constants.AttributesHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.TreeSet;

import static org.mockito.Mockito.when;

public class UserCommandTest extends LoginCommandTest{
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    HttpSession session;
    @Mock
    CourseServiceImpl courseService;


    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void return2CourcesWhenUserTutor() {
        ArrayList<Course> coursesToReturn = new ArrayList<>();
        Course mathCourse = new Course();
        mathCourse.setName("mathCourse");
        Course englishCourse = new Course();
        englishCourse.setName("englishCourse");
        coursesToReturn.add(englishCourse);
        coursesToReturn.add(mathCourse);
        when(courseService.findByTutor(Integer.valueOf(1))).thenReturn(coursesToReturn);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(AttributesHolder.ID)).thenReturn(1);
        when(session.getAttribute(AttributesHolder.ROLE)).thenReturn(User.TUTOR);
        UserCoursesCommand userCoursesCommand = new UserCoursesCommand();
        Assert.assertEquals(userCoursesCommand.execute(request,response),2);
    }


    @Test
    public void inputDataValid() {

    }
}
