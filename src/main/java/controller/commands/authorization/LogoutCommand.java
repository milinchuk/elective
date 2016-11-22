package controller.commands.authorization;

import controller.commands.Command;
import utils.PagesHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by click on 11/18/2016.
 */
public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // clear session
        //request.getSession().invalidate();
        return PagesHolder.LOGIN;
    }
}
