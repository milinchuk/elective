package model.dao.interfaces;

/**
 * DAO for general operations.
 * T - object for update, create.
 * P - object of primary key of object which will delete.
 *
 * @author Anastasia Milinchuk
 *
 * @see model.dao.interfaces.CourseDAO
 * @see model.dao.interfaces.ProgressDAO
 * @see model.dao.interfaces.UserDAO
 */
public interface GeneralDAO<T, P> {
    /**
     * Create object T
     *
     * @param t is object for create
     */
    void create(T t);

    /**
     * Update object T
     * @param t is object for updating
     */
    void update(T t);

    /**
     * Delete object with primary key P
     *
     * @param id is primary key of object
     */
    void delete(P id);
}
