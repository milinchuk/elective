package picker;

import model.entity.Course;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import utils.constants.AttributesHolder;
import utils.pickers.request.CourseRequestPicker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by click on 12/6/2016.
 */
public class CourseRequestPickerTest {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpSession session;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        when(request.getSession()).thenReturn(session);
    }

    @Test
    public void requestNull() {
        CourseRequestPicker picker = new CourseRequestPicker();
        Course course = picker.pick(null);
        assertNull(course);
    }

    @Test
    public void inputDataNull() {
        when(request.getSession().getAttribute(AttributesHolder.ID)).thenReturn(null);
        when(request.getParameter(AttributesHolder.NAME)).thenReturn(null);
        when(request.getParameter(AttributesHolder.ABOUT)).thenReturn(null);
        when(request.getParameter(AttributesHolder.ID)).thenReturn(null);
        CourseRequestPicker picker = new CourseRequestPicker();
        Course course = picker.pick(request);
        assertNull(course);
    }

    @Test
    public void inputDataValid() {

    }
}
