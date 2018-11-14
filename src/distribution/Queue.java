package distribution;

import java.util.ArrayList;

public class Queue {
    private ArrayList<Message> queue = new ArrayList<Message>();

    public Queue() {

    }

    public void enqueue(Message msg) {
        queue.add(msg);
    }

    public Message dequeue() {
        Message topo = queue.get(0);
        queue.remove(0);

        return topo;
    }

    public int queueSize() {
        return queue.size();
    }
}
