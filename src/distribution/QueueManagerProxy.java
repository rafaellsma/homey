package distribution;

import infrastructure.ClientRequestHandler;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class QueueManagerProxy implements IQueueManager {
    private String queueName;
    private String urlServer;
    private int portServer;
    private String hash;

    public QueueManagerProxy(String queueName){
        this.queueName = queueName;
        this.urlServer = "localhost";
        this.portServer = 1313;
    }

    @Override
    public void send(String m) throws IOException, InterruptedException, TimeoutException, ClassNotFoundException {
        ClientRequestHandler crh =  new ClientRequestHandler(urlServer, portServer);

        crh.send(Marshaller.marshall(requestPacket(m, OperationType.PUT)));

        setHash((ReplyPacket) Marshaller.unmarshall(crh.receive()));
    }

    @Override
    public String receive() throws IOException, InterruptedException, TimeoutException, ClassNotFoundException {
        ClientRequestHandler crh =  new ClientRequestHandler(urlServer, portServer);

        crh.send(Marshaller.marshall(requestPacket("", OperationType.GET)));

        ReplyPacket reply = (ReplyPacket) Marshaller.unmarshall(crh.receive());
        setHash(reply);

        return reply.getMessage().getBody().getBody();
    }

    private void subscribe() throws IOException, TimeoutException, ClassNotFoundException, InterruptedException {
        ClientRequestHandler crh =  new ClientRequestHandler(urlServer, portServer);

        crh.send(Marshaller.marshall(requestPacket("", OperationType.SUBSCRIBE)));

        setHash((ReplyPacket) Marshaller.unmarshall(crh.receive()));
    }

    private void setHash(ReplyPacket reply){
        this.hash = reply.getMessage().getHeader().getHash();
    }

    private RequestPacket requestPacket(String message, OperationType operationType){
        RequestPacket packet = new RequestPacket();
        Message requestMessage = new Message();

        requestMessage.setHeader(new MessageHeader(this.queueName, this.hash));
        requestMessage.setBody(new MessageBody(message));

        packet.setPacketBody(new RequestPacketBody(requestMessage));
        packet.setPacketHeader(new RequestPacketHeader(operationType));

        return packet;
    }
}
