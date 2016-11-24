package controller.commands.authorization;

import controller.commands.Command;
import utils.constants.PagesHolder;
import utils.constants.UrlHolder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by click on 11/20/2016.
 */
public class PageNotFoundCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return PagesHolder.PAGE_NOT_FOUND_JSP;
    }
}
