package thread.bounded;

import static thread.util.MyLogger.log;

public class ProducerTask implements Runnable {
    private final BoundedQueue boundedQueue;
    private final String request;
    public ProducerTask(BoundedQueue boundedQueue, String request) {
        this.boundedQueue = boundedQueue;
        this.request = request;
    }
    @Override
    public void run() {
        log("[생산 시도] " + request + " -> " + boundedQueue);
        boundedQueue.put(request);
        log("[생산 완료] " + request + " -> " + boundedQueue);
    }
}
