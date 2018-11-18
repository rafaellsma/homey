package distribution;

import java.io.Serializable;

public class RequestPacketHeader implements Serializable {
    private OperationType operation;

    public RequestPacketHeader(OperationType operation) {
        this.operation = operation;
    }

    public OperationType getOperation() {
        return operation;
    }

    public void setOperation(OperationType operation) {
        this.operation = operation;
    }
}
