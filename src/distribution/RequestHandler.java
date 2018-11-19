package distribution;

import infrastructure.ServerRequestHandler;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static distribution.Marshaller.marshall;

public class RequestHandler extends Thread {
    ServerRequestHandler SRH;
    RequestPacket request;
    QueueManager QM;

    public RequestHandler(ServerRequestHandler SRH, RequestPacket request, QueueManager QM){
        this.SRH = SRH;
        this.request = request;
        this.QM = QM;
    }

    public void run() {
        if(request.getPacketHeader().getOperation() == OperationType.PUT) {
            Message m = request.getPacketBody().getMessage();
            QM.receive(m);
            try {
                SRH.send(marshall(new ReplyPacket(QM.send(m.getHeader()), OperationType.PUT)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (request.getPacketHeader().getOperation() == OperationType.GET){
            MessageHeader header = request.getPacketBody().getMessage().getHeader();
            Message message = QM.send(header);
            try {
                SRH.send(marshall(new ReplyPacket(message, OperationType.GET)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (request.getPacketHeader().getOperation() == OperationType.GETNEXT){
            MessageHeader header = request.getPacketBody().getMessage().getHeader();
            try {
                Message message = QM.sendNext(header);
                if (message == null)
                SRH.send(marshall(new ReplyPacket(null, OperationType.LASTMESSAGE)));
                else
                SRH.send(marshall(new ReplyPacket(message, OperationType.GETNEXT)));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                try {
                    SRH.send(marshall(new ReplyPacket(QM.sendLastMessage(header), OperationType.LASTMESSAGE)));
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        } else {
            MessageHeader header = request.getPacketBody().getMessage().getHeader();
            QM.createQueue(header.getDestination());
            try {
                SRH.send(marshall(new ReplyPacket(QM.sendLastMessage(header), OperationType.LASTMESSAGE)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
