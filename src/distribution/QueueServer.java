package distribution;

import infrastructure.ServerRequestHandler;

import static distribution.Marshaller.unmarshall;

public class QueueServer {
    private int port;

    public void run() throws Exception {
        while (true) {
            ServerRequestHandler SRH = new ServerRequestHandler(port);
            RequestPacket rpk = (RequestPacket) unmarshall(SRH.receive());

        }
    }
}
