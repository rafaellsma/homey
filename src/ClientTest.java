import distribution.QueueManagerProxy;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ClientTest {
    public static void main(String[] args) {
        QueueManagerProxy queueManagerProxy = null;
        try {
            queueManagerProxy = new QueueManagerProxy("queue1");
            queueManagerProxy.send("dale1");
            queueManagerProxy.send("dale2");
            queueManagerProxy.send("dale3");
            String a = queueManagerProxy.receive();
            String b = queueManagerProxy.receive();
            System.out.println(a);
            System.out.println(b);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
