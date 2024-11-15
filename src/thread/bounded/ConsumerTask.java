package thread.bounded;

import static thread.util.MyLogger.log;

public class ConsumerTask implements Runnable {
    private final BoundedQueue boundedQueue;
    public ConsumerTask(BoundedQueue boundedQueue) {
        this.boundedQueue = boundedQueue;
    }
    @Override
    public void run() {
        log("[소비 시도] ? <- " + boundedQueue);
        String data = boundedQueue.take();
        log("[소비 완료] " + data + " <- " + boundedQueue);
    }
}
