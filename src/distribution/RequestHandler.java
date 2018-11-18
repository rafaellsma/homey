package distribution;

import infrastructure.ServerRequestHandler;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static distribution.OperationType.GET;
import static distribution.OperationType.PUT;
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

    @Override
    public void run() {
        if(request.getPacketHeader().getOperation() == OperationType.PUT) {
            Message m = request.getPacketBody().getMessage();
            QM.receive(m);
            try {
                SRH.send(marshall(new ReplyPacket(QM.send(m.getHeader()))));
            } catch (IOException e) {
                e.printStackTrace();
            }
            //TODO ADD REPLY PACKET
        } else if (request.getPacketHeader().getOperation() == GET){
            MessageHeader header = request.getPacketBody().getMessage().getHeader();
            Message message = QM.send(header);
            try {
                SRH.send(marshall(new ReplyPacket(message)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            //TODO ADD REPLY PACKET
        } else {
            MessageHeader header = request.getPacketBody().getMessage().getHeader();
            QM.createQueue(header.getDestination());
            //TODO ADD REPLY PACKET
        }
    }
}
