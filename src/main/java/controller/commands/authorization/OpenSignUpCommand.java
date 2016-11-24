package controller.commands.authorization;

import controller.commands.Command;
import utils.constants.PagesHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by click on 11/24/2016.
 */
public class OpenSignUpCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return PagesHolder.SIGNUP;
    }
}
