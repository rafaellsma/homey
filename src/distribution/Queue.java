package distribution;

import java.util.HashMap;
import java.util.Random;

public class Queue {
    private HashMap<String, Message> messages = new HashMap();
    private HashMap<String, String> next = new HashMap();
    private String last = "";
    private Random random = new Random();
    private int sze = 0;
    private int targetStringLength = 10;

    private String genNextId() {
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = (int) (random.nextFloat() * 16);
            if (randomLimitedInt < 10) randomLimitedInt += '0';
            else randomLimitedInt += 'A' - 10;
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();

        return generatedString;
    }

    public Queue() {
        next.put(last, "");
        messages.put(last, null);
    }

    public void push(Message msg) {
        String nxt = genNextId();
        msg.getHeader().setHash(nxt);
        next.put(nxt, "");
        next.put(last, nxt);
        last = nxt;
        messages.put(last, msg);
        sze++;
    }

    public String getCurrent() {
        return last;
    }

    public Message get(String s) {
        return messages.get(s);
    }

    public String getNext(String s) {
        return next.get(s);
    }

    public int queueSize() {
        return sze;
    }
}
