package utils.pickers.request;

import utils.pickers.Picker;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by click on 11/22/2016.
 */
public abstract class RequestPicker<T> implements Picker<T, HttpServletRequest> {
    @Override
    public abstract T pick(HttpServletRequest request);
}
