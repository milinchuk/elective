package validators;

import validators.entity.Errors;

/**
 * Interface Validator for validation data.
 * Exist two methods: with object for validation and errors.
 * It method set errors with some messages and fields. It method check all fields.
 *
 * Second method check only for first entrance of error.
 *
 * @author Anastasia Milinchuk
 * @see validators.entity.Errors
 */
public interface Validator<T> {
    /**
     * Validation for all fields
     *
     * @param t is object that will validate
     * @param errors is errors in which this method will set new error
     * @return true if validate is correct and false on the other case
     */
    public boolean validate(T t, Errors errors);

    /**
     * Validation for first entrance of error
     * @param t is object that will validate
     * @return true if validate is correct and false on the other case
     */
    public boolean validate(T t);
}
