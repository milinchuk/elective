package controller.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This is command which execute certain logic.
 * Perform role of controller.
 *
 * @author Anastasia Milinchuk
 */
public interface Command {
    /**
     * This method execute some logic according to request
     *
     * @param request is request from client
     * @param response is response to client
     * @return page path
     */
    public String execute(HttpServletRequest request, HttpServletResponse response);
}
