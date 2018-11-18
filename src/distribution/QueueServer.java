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
        ServerRequestHandler SRH = new ServerRequestHandler(port);

        while (true) {
            RequestPacket requestPacket = (RequestPacket) Marshaller.unmarshall(SRH.receive());
            RequestHandler handleThread = new RequestHandler(SRH, requestPacket, QM);
            handleThread.run();
        }
    }
}
