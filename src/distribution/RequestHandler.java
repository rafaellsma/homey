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

    RequestHandler(ServerRequestHandler SRH, RequestPacket request, QueueManager QM){
        super();
        this.SRH = SRH;
        this.request = request;
        this.QM = QM;
    }

    @Override
    public void run() {
        super.run();
        if(request.getPacketHeader().getOperation() == PUT) {
            Message m = request.getPacketBody().getMessage();
            QM.createQueue(m.getHeader().getDestination());
            QM.receive(m);
        } else if (request.getPacketHeader().getOperation() == GET){
            MessageHeader header = request.getPacketBody().getMessage().getHeader();
            Message message = QM.send(header);
            try {
                SRH.send(marshall(message));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {

        }
    }
}
