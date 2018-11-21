package application;

import distribution.QueueManagerProxy;

public class Publisher {
    private QueueManagerProxy QMP;

    public Publisher(String topic, String host, int port){
        this.QMP = new QueueManagerProxy(topic, host, port);
    }

    public void publish(String message){
        QMP.send(message);
    }
}
