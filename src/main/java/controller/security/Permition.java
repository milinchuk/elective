package controller.security;

import model.entity.User;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by click on 11/21/2016.
 */
public class Permition {
    public boolean hasPermition(HttpServletRequest request, User user){
        user.setId(3);
        user.setEmail("admin@gmail.com");
        user.setRole(User.TUTOR);
        request.getSession().setAttribute("user", user);
        return true;
    }

    public int getRole(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        return user.getRole();
    }
}
