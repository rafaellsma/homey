import distribution.QueueServer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ServerTest {
    public static void main(String[] args) {
        QueueServer server = new QueueServer(3001);
        try {
            server.run();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
