package validators.entity;

/**
 * Created by click on 11/25/2016.
 */
public class Message {
    private String attribute;
    private String message;

    public Message() {
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
