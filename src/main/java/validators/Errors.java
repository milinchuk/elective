package validators;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by click on 11/20/2016.
 */
public class Errors {
    private List<Message> messages;
    private boolean result = true;

    public Errors() {
        messages = new ArrayList<>();
    }

    public void addMessage(Message message){
        messages.add(message);
    }

    public void addMessage(String attribute, String message) {
        Message msg = new Message();
        msg.setAttribute(attribute);
        msg.setMessage(message);
        messages.add(msg);
    }

    public List<Message> getMessages() {
        return messages;
    }

    public boolean hasError(){
        return !result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
