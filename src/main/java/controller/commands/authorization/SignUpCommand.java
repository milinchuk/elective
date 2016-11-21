package controller.commands.authorization;

import controller.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by click on 11/18/2016.
 */
public class SignUpCommand implements Command {

    public static final String SIGNUP = "WEB-INF/signup.jsp";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return SIGNUP;
    }
}
