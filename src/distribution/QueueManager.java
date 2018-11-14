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
        queues.get(msg.getHeader()).push(msg);
    }

    public boolean isLastMessage(String queueName, String hash) {
        return queues.get(queueName).getCurrent() == hash;
    }

    public Message getMessage(String queueName, String hash) {
        Queue queue = queues.get(queueName);

        return queue.get(hash);
    }

    public Message getNextMessage(String queueName, String hash) {
        Queue queue = queues.get(queueName);
        assert(!isLastMessage(queueName, hash)): "Last message";
        return queue.get(queue.getNext(hash));
    }
}
