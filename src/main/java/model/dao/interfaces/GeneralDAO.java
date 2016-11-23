package model.dao.interfaces;

/**
 * Created by click on 11/21/2016.
 */
public interface GeneralDAO<T> {
    void create(T t);
    void update(T t);
}
