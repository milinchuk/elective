package controller.commands.authorization;

import controller.commands.Command;
import utils.constants.PagesHolder;
import utils.constants.UrlHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by click on 11/18/2016.
 */
public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // clear session
        request.getSession().invalidate();
        return UrlHolder.LOGIN;
    }
}
