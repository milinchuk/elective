package controller.commands.authorization;

import controller.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by click on 11/20/2016.
 */
public class PageNotFoundCommand implements Command {
    public static final String PAGE_NOT_FOUND = "/WEB-INF/pageNotFound.jsp";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return PAGE_NOT_FOUND;
    }
}
