package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static thread.util.MyLogger.log;

public class BoundedQueueV4 implements BoundedQueue {

    /*
        lock.newCondition(): 스레드 대기 공간 생성

        참고
            Object.wait() 에서 사용한 스레드 대기 공간은 모든 객체 인스턴스가 내부에 기본으로 가지고 있다.
            Lock(ReentrantLock) 을 사용하는 경우 이렇게 스레드 대기 공간을 직접 만들어서 사용해야 한다.
     */
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();


    private final Queue<String> queue = new ArrayDeque<>();
    private final int max;
    public BoundedQueueV4(int max) {
        this.max = max;
    }

    /*
        condition.await()
            Object.wait() 와 유사한 기능이다.
            지정한 condition 에 현재 스레드를 대기( WAITING ) 상태로 보관한다.
            이 때 ReentrantLock 에서 획득한 락을 반납하고 대기 상태로 condition 에 보관된다.

        condition.signal()
            Object.notify() 와 유사한 기능이다.
            지정한 condition 에서 대기중인 스레드를 하나 깨운다.
            깨어난 스레드는 condition 에서 빠져나온다.
     */

    @Override
    public void put(String data) {
        lock.lock();
        try {
            while (queue.size() == max) {
                log("[put] 큐가 가득 참, 생산자 대기");
                try {
                    condition.await();
                    log("[put] 생산자 깨어남");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            queue.offer(data);
            log("[put] 생산자 데이터 저장, signal() 호출");
            condition.signal();
        } finally {
            lock.unlock();
        }

    }

    @Override
    public String take() {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                log("[take] 큐에 데이터가 없음, 소비자 대기");
                try {
                    condition.await();
                    log("[take] 소비자 깨어남");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            String data = queue.poll();
            log("[take] 소비자 데이터 획득, signal() 호출");
            condition.signal();
            return data;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        return queue.toString();
    }

}
