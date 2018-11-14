package distribution;

import java.util.HashMap;

public class QueueManager {
    private HashMap<String, Queue> queues = new HashMap<String, Queue>();

    public QueueManager() {
    }

    public void createQueue(String queueName) {
        queues.putIfAbsent(queueName, new Queue());
    }

    public void storeInQueue(Message msg) {
        queues.get(msg.getHeader().getDestination()).push(msg);
    }

    public boolean isLastMessage(MessageHeader header) {
        return queues.get(header.getDestination()).getCurrent() == header.getHash();
    }

    public Message getMessage(MessageHeader header) {
        Queue queue = queues.get(header.getDestination());

        return queue.get(header.getHash());
    }

    public Message getNextMessage(MessageHeader header) {
        String queueName = header.getDestination();
        String hash = header.getHash();
        Queue queue = queues.get(header.getDestination());
        
        assert(!isLastMessage(header)): "Last message";
        return queue.get(queue.getNext(hash));
    }
}
