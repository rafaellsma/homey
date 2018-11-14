package distribution;

public class RequestPacketHeader {
    private OperationType operation;

    public RequestPacketHeader(OperationType operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(OperationType operation) {
        this.operation = operation;
    }
}
