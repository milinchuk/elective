package entity;

import model.entity.User;

/**
 * Created by click on 12/1/2016.
 */
public enum Users {
    TUTOR(1, "tutor@gmail.com", "b59c67bf196a4758191e42f76670ceba", "Tutor", "Tutor", 1),
    STUDENT(2,"student@gmail.com","b59c67bf196a4758191e42f76670ceba","Student","Student",2);

    public final User user;

    Users(int id, String email, String password, String firstName, String lastName, int role) {
        user = new User();
        user.setId(id);
        user.setEmail(email);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRole(role);
    }
}
