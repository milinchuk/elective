package validators;

import validators.entity.Errors;

/**
 * Created by click on 11/20/2016.
 */
public interface Validator<T> {
    public boolean validate(T t, Errors errors);
    public boolean validate(T t);
}
