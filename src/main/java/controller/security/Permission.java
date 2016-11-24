package controller.security;

import model.entity.User;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by click on 11/21/2016.
 */
public class Permission {
    public boolean hasPermission(HttpServletRequest request){
        request.getSession().setAttribute("id", 5);
        request.getSession().setAttribute("role", 2);
        return true;
    }

    public int getRole(HttpServletRequest request){
        return Integer.parseInt(request.getSession().getAttribute("role").toString());
    }
}
