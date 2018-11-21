package application;

import distribution.QueueManagerProxy;

import java.util.Observable;

public class Subscriber extends Observable implements Runnable {
    private QueueManagerProxy QMP;

    public Subscriber(String topic, String host, int port) {
        this.QMP = new QueueManagerProxy(topic, host, port);
    }

    @Override
    public void run() {
        while (true) {
            String response = QMP.receive();
            if (response != null) {
                setChanged();
                notifyObservers(response);
            }
        }
    }
}
