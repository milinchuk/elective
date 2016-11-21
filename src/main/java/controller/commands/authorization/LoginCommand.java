package controller.commands.authorization;

import controller.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by click on 11/18/2016.
 */
public class LoginCommand implements Command {

    public static final String LOGIN = "/WEB-INF/login.jsp";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return LOGIN;
    }
}
