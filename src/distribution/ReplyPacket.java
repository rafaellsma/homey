package distribution;

import java.io.Serializable;

public class ReplyPacket implements Serializable {
    private Message message;
    public ReplyPacket(Message message){
        this.message = message;
    }
    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
