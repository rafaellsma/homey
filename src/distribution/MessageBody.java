package distribution;

import java.io.Serializable;

public class MessageBody implements Serializable {
    private String body;

    public MessageBody(String body){
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
