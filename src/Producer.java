import distribution.QueueManagerProxy;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {
    public static void main(String[] args) {
        QueueManagerProxy queueManagerProxy = null;
        try {
            queueManagerProxy = new QueueManagerProxy("queue1");
            while (true) {
                queueManagerProxy.send("dale1");
            }
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