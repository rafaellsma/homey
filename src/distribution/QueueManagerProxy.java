package distribution;

import infrastructure.ClientRequestHandler;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class QueueManagerProxy implements IQueueManager {
    private String queueName;
    private String urlServer;
    private int portServer;
    private String hash;

    public QueueManagerProxy(String queueName) {
        this.queueName = queueName;
        this.urlServer = "localhost";
        this.portServer = 3001;
        subscribe();
    }

    public QueueManagerProxy(String queueName, String urlServer, int portServer) {
        this.queueName = queueName;
        this.urlServer = urlServer;
        this.portServer = portServer;
        subscribe();
    }

    @Override
    public void send(String m) {
        try {
            ClientRequestHandler crh = new ClientRequestHandler(urlServer, portServer);

            crh.send(Marshaller.marshall(requestPacket(m, OperationType.PUT)));

            ReplyPacket replyPacket = (ReplyPacket) Marshaller.unmarshall(crh.receive());

            crh.close();
        } catch (IOException | TimeoutException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String receive() {
        try {
            ClientRequestHandler crh = new ClientRequestHandler(urlServer, portServer);

            crh.send(Marshaller.marshall(requestPacket("", OperationType.GETNEXT)));

            ReplyPacket reply = (ReplyPacket) Marshaller.unmarshall(crh.receive());

            crh.close();

            if (reply.getOperationType() != OperationType.LASTMESSAGE) {
                setHash(reply);

                return reply.getMessage().getBody().getBody();
            }
        } catch (IOException | TimeoutException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void subscribe() {
        ClientRequestHandler crh = null;
        try {
            crh = new ClientRequestHandler(urlServer, portServer);
            crh.send(Marshaller.marshall(requestPacket("", OperationType.SUBSCRIBE)));

            setHash((ReplyPacket) Marshaller.unmarshall(crh.receive()));

            crh.close();
        } catch (IOException | TimeoutException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void setHash(ReplyPacket reply) {
        Message msg = reply.getMessage();
        try {
            MessageHeader header = msg.getHeader();
            this.hash = header.getHash();
        } catch (NullPointerException w) {
            w.printStackTrace();
        }
    }

    private RequestPacket requestPacket(String message, OperationType operationType) {
        RequestPacket packet = new RequestPacket();
        Message requestMessage = new Message();

        requestMessage.setHeader(new MessageHeader(this.queueName, this.hash));
        requestMessage.setBody(new MessageBody(message));

        packet.setPacketBody(new RequestPacketBody(requestMessage));
        packet.setPacketHeader(new RequestPacketHeader(operationType));

        return packet;
    }
}
