package distribution;

import infrastructure.ServerRequestHandler;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static distribution.Marshaller.marshall;
import static distribution.Marshaller.unmarshall;

public class QueueServer {
    private int port;
    private QueueManager QM;

    public QueueServer(int port){
        this.port = port;
        this.QM = new QueueManager();
    }

    public void run() throws IOException, TimeoutException, ClassNotFoundException {
        while (true) {
            ServerRequestHandler SRH = new ServerRequestHandler(port);
            RequestPacket requestPacket = (RequestPacket) Marshaller.unmarshall(SRH.receive());
            Thread handleThread = new RequestHandler(SRH, requestPacket, QM);
            handleThread.start();
        }
    }
}
