package thread.bounded;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import static thread.util.MyLogger.log;

public class BoundedQueueV6_4 implements BoundedQueue {

    /*
        add(data)
            성공하면 true 를 반환하고, 버퍼가 가득 차면 즉시 예외가 발생한다.
            java.lang.IllegalStateException: Queue full

        remove()
            버퍼에 데이터가 없으면, 즉시 예외가 발생한다.
            java.util.NoSuchElementException
     */

    private BlockingQueue<String> queue;
    public BoundedQueueV6_4(int max) {
        queue = new ArrayBlockingQueue<>(max);
    }

    @Override
    public void put(String data) {
        queue.add(data); // java.lang.IllegalStateException: Queue full
    }

    @Override
    public String take() {
        return queue.remove(); // java.util.NoSuchElementException
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
