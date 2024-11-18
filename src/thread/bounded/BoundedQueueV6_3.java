package thread.bounded;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import static thread.util.MyLogger.log;

public class BoundedQueueV6_3 implements BoundedQueue {

    /*
        offer(data, 시간)
            성공하면 true 를 반환하고, 버퍼가 가득 차서 스레드가 대기해야 하는 상황이면,
            지정한 시간까지 대기한다. 대기 시간을 지나면 false 를 반환한다.
            여기서는 확인을 목적으로 1 나노초( NANOSECONDS )로 설정했다.

        poll(시간)
            버퍼에 데이터가 없어서 스레드가 대기해야 하는 상황이면, 지정한 시간까지 대기한다.
            대기 시간을 지나면 null 을 반환한다.
            여기서는 2초( SECONDS )로 설정했다.
     */

    private BlockingQueue<String> queue;
    public BoundedQueueV6_3(int max) {
        queue = new ArrayBlockingQueue<>(max);
    }

    @Override
    public void put(String data) {
        try {
            // 대기 시간 설정 가능
            boolean result = queue.offer(data, 1, TimeUnit.NANOSECONDS);
            log("저장 시도 결과 = " + result);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String take() {
        try {
            // 대기 시간 설정 가능
            return queue.poll(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
