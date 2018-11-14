package distribution;

import infrastructure.ServerRequestHandler;

import static distribution.OperationType.GET;
import static distribution.OperationType.PUT;

public class RequestHandler extends Thread {
    ServerRequestHandler SRH;
    RequestPacket request;
    QueueManager QM;

    RequestHandler(ServerRequestHandler SRH, RequestPacket request, QueueManager QM){
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
            QM.storeInQueue(m);
        } else if (request.getPacketHeader().getOperation() == GET){

        } else {

        }
    }
}
