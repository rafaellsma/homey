import application.Subscriber;
import distribution.QueueManagerProxy;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {
    public static void main(String[] args) {
        Subscriber subscriber = new Subscriber("lampadasQuarto","localhost", 3001);
        subscriber.addObserver((o, arg) -> {
            System.out.println(arg);
        });
        Thread thread = new Thread(subscriber);
        thread.start();
    }
}
