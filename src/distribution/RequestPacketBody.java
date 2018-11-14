package distribution;

public class RequestPacketBody {
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
