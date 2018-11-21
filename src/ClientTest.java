import distribution.QueueManagerProxy;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ClientTest {
    public static void main(String[] args) {
        QueueManagerProxy queueManagerProxy = null;

        while (true) {
            queueManagerProxy.send("dale1");
            queueManagerProxy.send("dale2");
            queueManagerProxy.send("dale3");
            String a = queueManagerProxy.receive();
            String b = queueManagerProxy.receive();
            System.out.println(a);
            System.out.println(b);
        }
    }
}
