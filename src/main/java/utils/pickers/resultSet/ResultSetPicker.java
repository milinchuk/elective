package utils.pickers.resultSet;

import utils.pickers.Picker;

import java.sql.ResultSet;

/**
 * Created by click on 11/6/2016.
 */
public interface ResultSetPicker<T> extends Picker<T, ResultSet> {
    public T pick(ResultSet resultSet);
}
