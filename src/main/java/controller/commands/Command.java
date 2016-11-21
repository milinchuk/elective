package controller.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by click on 11/15/2016.
 */
public interface Command {
    public String execute(HttpServletRequest request, HttpServletResponse response);
}
