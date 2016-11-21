package controller.commands.user;

import controller.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by click on 11/18/2016.
 */
public class ProfileCommand implements Command {

    public static final String PROFILE = "/WEB-INF/profile.jsp";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return PROFILE;
    }
}
