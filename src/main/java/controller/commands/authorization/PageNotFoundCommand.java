package controller.commands.authorization;

import controller.commands.Command;
import utils.constants.PagesHolder;
import utils.constants.UrlHolder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Anastasia Milinchuk
 */
public class PageNotFoundCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return PagesHolder.PAGE_NOT_FOUND_JSP;
    }
}
