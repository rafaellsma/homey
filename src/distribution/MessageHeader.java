package distribution;

import java.io.Serializable;

public class MessageHeader implements Serializable {
    private String destination;
    private String hash;

    public MessageHeader(String destination, String hash) {
        this.destination = destination;
        this.hash = hash;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getHash() {
        return hash;
    }

    public set setHash(String hash) {
        this.hash = hash;
    }
}
