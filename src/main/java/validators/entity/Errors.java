package validators.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by click on 11/20/2016.
 */
public class Errors {
    private Map<String, String> messages;
    private boolean result = true;

    public Errors() {
        messages = new HashMap<>();
    }

    public void addMessage(String attribute, String message) {
        messages.put(attribute, message);
    }

    public Map<String, String> getMessages() {
        return messages;
    }

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
