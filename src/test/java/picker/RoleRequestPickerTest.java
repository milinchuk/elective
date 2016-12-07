package picker;

import model.entity.Progress;
import model.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import utils.constants.AttributesHolder;
import utils.pickers.request.ProgressRequestPicker;
import utils.pickers.request.RoleRequestPicker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

/**
 * Created by click on 12/7/2016.
 */
public class RoleRequestPickerTest {
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
        RoleRequestPicker picker = new RoleRequestPicker();
        Integer role = picker.pick(null);
        assertNull(role);
    }

    @Test
    public void faultData() {
        when(request.getParameter(AttributesHolder.ROLE)).thenReturn(null);
        RoleRequestPicker picker = new RoleRequestPicker();
        Integer role = picker.pick(request);
        assertNull(role);
    }

    @Test
    public void studentRoleTest(){
        when(request.getParameter(AttributesHolder.ROLE)).thenReturn(AttributesHolder.STUDENT);
        RoleRequestPicker picker = new RoleRequestPicker();
        Integer role = picker.pick(request);
        assertEquals(role, new Integer(User.STUDENT));
    }

    @Test
    public void tutorRoleTest(){
        when(request.getParameter(AttributesHolder.ROLE)).thenReturn(AttributesHolder.TUTOR);
        RoleRequestPicker picker = new RoleRequestPicker();
        Integer role = picker.pick(request);
        assertEquals(role, new Integer(User.TUTOR));
    }
}
