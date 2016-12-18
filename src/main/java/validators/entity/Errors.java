package validators.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Object holders errors in map. Key is field that has been incorrect,
 * value is a message of incorrectness.
 * Errors is DTO that uses in controller for validation multiple entities or multiple fields.
 * Setting of values goes in Validators.
 *
 * @author Anastasia Milinchuk
 *
 * @see validators.Validator
 * @see controller.commands.Command
 */
public class Errors {
    /**
     * Key is field that has been incorrect,
     * value is a message of incorrectness.
     */
    private Map<String, String> messages;

    /**
     * This value show if object has at least one error.
     * If result true - object haven't error. On the other case
     * object has error.
     */
    private boolean result = true;

    public Errors() {
        messages = new HashMap<>();
    }

    /**
     * Add message of incorrectness
     *
     * @param attribute is incorrect attribute
     * @param message is message that describe why attribute is incorrect
     */
    public void addMessage(String attribute, String message) {
        messages.put(attribute, message);
    }

    public Map<String, String> getMessages() {
        return messages;
    }

    /**
     * Retrieve if object has at least one error
     *
     * @return true in case if object has error, false if object hasn't
     */
    public boolean hasError(){
        return !result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public boolean getResult() {
        return result;
    }
}
