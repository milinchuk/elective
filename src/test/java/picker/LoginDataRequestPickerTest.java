package picker;

import model.entity.Course;
import model.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import utils.constants.AttributesHolder;
import utils.pickers.request.CourseRequestPicker;
import utils.pickers.request.LoginDataRequestPicker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by click on 12/6/2016.
 */
public class LoginDataRequestPickerTest {
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
    public void invalidData() {
        LoginDataRequestPicker picker = new LoginDataRequestPicker();
        User user = picker.pick(null);
        assertNull(user);
    }

    @Test
    public void faultData() {
        when(request.getParameter(AttributesHolder.EMAIL)).thenReturn(null);
        when(request.getParameter(AttributesHolder.PASSWORD)).thenReturn(null);
        LoginDataRequestPicker picker = new LoginDataRequestPicker();
        User user = picker.pick(request);
        assertNotNull(user);
    }
}
