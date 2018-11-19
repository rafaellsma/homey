package distribution;

import infrastructure.ServerRequestHandler;

import java.io.IOException;
import java.net.ServerSocket;
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
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            ServerRequestHandler SRH = new ServerRequestHandler(serverSocket.accept());
            new Thread(){
                @Override
                public void run() {
                    RequestPacket requestPacket = null;
                    try {
                        requestPacket = (RequestPacket) Marshaller.unmarshall(SRH.receive());
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    RequestHandler handleThread = new RequestHandler(SRH, requestPacket, QM);
                    handleThread.run();
                }
            }.start();
        }
    }
}
