package validators;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by click on 11/20/2016.
 */
public class Errors {
    private List<String> messages;
    private boolean result;

    public Errors() {
        messages = new ArrayList<>();
    }

    public void addMessage(String message){
        messages.add(message);
    }

    public List<String> getMessages() {
        return messages;
    }

    public boolean hasError(){
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
