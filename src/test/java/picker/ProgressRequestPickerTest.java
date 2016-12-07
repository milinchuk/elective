package picker;

import model.entity.Progress;
import model.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import utils.constants.AttributesHolder;
import utils.pickers.request.ProfileRequestPicker;
import utils.pickers.request.ProgressRequestPicker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

/**
 * Created by click on 12/7/2016.
 */
public class ProgressRequestPickerTest {
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
        ProgressRequestPicker picker = new ProgressRequestPicker();
        Progress progress = picker.pick(null);
        assertNull(progress);
    }

    @Test
    public void faultData() {
        when(request.getParameter(AttributesHolder.ID)).thenReturn(null);
        when(request.getParameter(AttributesHolder.COURSE)).thenReturn(null);
        ProgressRequestPicker picker = new ProgressRequestPicker();
        Progress progress = picker.pick(request);
        assertNull(progress);
    }
}
