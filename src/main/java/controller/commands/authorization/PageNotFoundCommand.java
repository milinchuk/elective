package controller.commands.authorization;

import controller.commands.Command;
import utils.constants.PagesHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Anastasia Milinchuk
 */
public class PageNotFoundCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return PagesHolder.PAGE_NOT_FOUND_JSP;
    }
}
