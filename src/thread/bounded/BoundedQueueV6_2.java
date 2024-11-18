package thread.bounded;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static thread.util.MyLogger.log;

public class BoundedQueueV6_2 implements BoundedQueue {

    /*
        offer(), poll()
            두 메서드는 스레드가 대기하지 않는다.
            offer(data) 는 성공하면 true 를 반환하고, 버퍼가 가득 차면 즉시 false 를 반환한다.
            poll() 버퍼에 데이터가 없으면 즉시 null 을 반환한다.
     */

    private BlockingQueue<String> queue;
    public BoundedQueueV6_2(int max) {
        queue = new ArrayBlockingQueue<>(max);
    }

    @Override
    public void put(String data) {
        boolean result = queue.offer(data);
        log("저장 시도 결과 = " + result);
    }

    @Override
    public String take() {
        return queue.poll();
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
