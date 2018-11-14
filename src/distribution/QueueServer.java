package distribution;

import infrastructure.ServerRequestHandler;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static distribution.Marshaller.marshall;
import static distribution.Marshaller.unmarshall;

public class QueueServer {
    private int port;

    public void run() throws IOException, TimeoutException, ClassNotFoundException {
        while (true) {
            ServerRequestHandler SRH = new ServerRequestHandler(port);
            RequestPacket requestPacket = (RequestPacket) Marshaller.unmarshall(SRH.receive());
        }
    }
}
