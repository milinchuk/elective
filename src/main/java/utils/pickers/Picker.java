package utils.pickers;

/**
 * Created by click on 11/22/2016.
 */
public interface Picker<T, S> {
    T pick(S s);
}
