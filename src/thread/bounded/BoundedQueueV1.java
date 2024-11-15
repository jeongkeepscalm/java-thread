package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;

import static thread.util.MyLogger.log;

public class BoundedQueueV1 implements BoundedQueue {

    private final Queue<String> queue = new ArrayDeque<>();
    private final int max;
    public BoundedQueueV1(int max) {
        this.max = max;
    }

    /**
     * private final Queue<String> queue = new ArrayDeque<>();
     *      여러 스레드가 접근할 예정이므로 synchronized 를 사용해서
     *      한 번에 하나의 스레드만 put() 또는 take() 를 실행할 수 있도록 안전한 임계 영역을 만든다.
     */

    @Override
    public synchronized void put(String data) {
        if (queue.size() == max) {
            log("[put] 큐가 가득 참, 버림: " + data);
            return;
        }
        queue.offer(data);
    }

    @Override
    public synchronized String take() {
        if (queue.isEmpty()) {
            return null;
        }
        return queue.poll();
    }

    @Override
    public String toString() {
        return queue.toString();
    }

}
