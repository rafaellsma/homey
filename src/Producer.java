import application.Publisher;
import distribution.QueueManagerProxy;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {
    public static void main(String[] args) {
        Publisher publisher = new Publisher("lampadasQuarto", "localhost", 3001);

        while (true) {
            publisher.publish("ltx++");
        }

    }
}