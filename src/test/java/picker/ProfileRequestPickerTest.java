package picker;

import model.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import utils.constants.AttributesHolder;
import utils.pickers.request.LoginDataRequestPicker;
import utils.pickers.request.ProfileRequestPicker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by click on 12/7/2016.
 */
public class ProfileRequestPickerTest {
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
        ProfileRequestPicker picker = new ProfileRequestPicker();
        User user = picker.pick(null);
        assertNull(user);
    }

    @Test
    public void faultData() {
        when(request.getParameter(AttributesHolder.EMAIL)).thenReturn(null);
        when(request.getParameter(AttributesHolder.FIRST_NAME)).thenReturn(null);
        when(request.getParameter(AttributesHolder.LAST_NAME)).thenReturn(null);
        when(request.getSession().getAttribute(AttributesHolder.ID)).thenReturn(null);
        ProfileRequestPicker picker = new ProfileRequestPicker();
        User user = picker.pick(request);
        assertNull(user);
    }
}
