package distribution;

import java.io.Serializable;

public class ReplyPacket implements Serializable {
    private Message message;
    private OperationType operationType;

    public ReplyPacket(Message message, OperationType operationType){
        this.message = message;
        this.operationType = operationType;
    }
    
    public Message getMessage() {
        return message;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
