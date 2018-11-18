package distribution;

import java.io.Serializable;

public class RequestPacketBody implements Serializable {
    private Message message;

    public RequestPacketBody(Message message){
        this.message = message;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
