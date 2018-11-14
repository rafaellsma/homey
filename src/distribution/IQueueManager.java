package distribution;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public interface IQueueManager {
    void send(String m) throws IOException, InterruptedException, TimeoutException, ClassNotFoundException;
    String receive() throws IOException, InterruptedException, TimeoutException, ClassNotFoundException;
}
