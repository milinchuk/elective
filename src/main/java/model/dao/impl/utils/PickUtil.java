package model.dao.impl.utils;

import java.sql.ResultSet;

/**
 * Created by click on 11/6/2016.
 */
public interface PickUtil<T> {
    public T pick(ResultSet resultSet);
}
