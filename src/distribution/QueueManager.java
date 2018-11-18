package distribution;

import java.util.HashMap;

public class QueueManager {
    private HashMap<String, Queue> queues = new HashMap<String, Queue>();

    public QueueManager() {
    }

    public void createQueue(String queueName) {
        queues.putIfAbsent(queueName, new Queue());
    }

    public void receive(Message msg) {
        queues.get(msg.getHeader().getDestination()).push(msg);
    }

    public boolean isLastMessage(MessageHeader header) {
        return queues.get(header.getDestination()).getCurrent() == header.getHash();
    }

    public Message send(MessageHeader header) {
        Queue queue = queues.get(header.getDestination());

        return queue.get(header.getHash());
    }

    public Message sendNext(MessageHeader header) throws NullPointerException {
        String hash = header.getHash();
        Queue queue = queues.get(header.getDestination());

        if(isLastMessage(header)) {
            throw new NullPointerException();
        }
        return queue.get(queue.getNext(hash));
    }
}
