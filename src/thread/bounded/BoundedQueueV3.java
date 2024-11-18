package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;

import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

/*
    wait() 로 대기 상태에 빠진 스레드는 notify() 를 사용해야 깨울 수 있다.
    생산자는 생산을 완료하면 notify()로 대기하는 스레드를 깨워서 생산된 데이터를 가져가게 하고,
    소비자는 소비를 완료하면 notify() 로 대기하는 스레드를 깨워서 데이터를 생산하라고 하면 된다.
    여기서 중요한 핵심은 wait() 를 호출해서 대기 상태에 빠질 때 락을 반납하고 대기 상태에 빠진다는 것이다.
    대기 상태에 빠지면 어차피 아무일도 하지 않으므로 락도 필요하지 않다.
    
    v3 문제점
        소비자 프로세스가 먼저 실행되었을 때, 모든 소비자 스레드가 스레드 대기 집합에 있게되고,
        생산자 프로세스가 실행되어 데이터가 큐에 삽입되고 notify() 로 스레드 대기 집합의 소비자 스레드가 실행된다.
        그 후, 다시 notify() 로 스레드 대기 집합의 소비자 스레드를 호출하는 불필요한 호출 발생

        => 소비자인 c1 이 같은 소비자인 c2 , c3 를 깨울 수 있었다. 이 경우 큐에 데이터가 없을 가능성이 있다.
        이때는 깨어난 소비자 스레드가 CPU 자원만 소모하고 다시 대기 집합에 들어갔기 때문에 비효율적이다.

    notify() 문제점
        스레드 기아: 어떤 스레드가 깨어날 지 알 수 없기 때문에 발생할 수 있는 스레드 기아 문제가 있다.

        해결방안: notifyAll()
            스레드 대기 집합에 있는 모든 스레드를 한번에 다 깨울 수 있다.
            깨워진 스레드는 락 획득일 시도하게 되어 같은 스레드는 다시 대기 집합에 들어가게 되고 결국 다른 스레드가 락을 획득하게 된다.
 */
public class BoundedQueueV3 implements BoundedQueue {

    private final Queue<String> queue = new ArrayDeque<>();
    private final int max;
    public BoundedQueueV3(int max) {
        this.max = max;
    }

    /**
     * private final Queue<String> queue = new ArrayDeque<>();
     *      여러 스레드가 접근할 예정이므로 synchronized 를 사용해서
     *      한 번에 하나의 스레드만 put() 또는 take() 를 실행할 수 있도록 안전한 임계 영역을 만든다.
     */

    @Override
    public synchronized void put(String data) {
        while (queue.size() == max) {
            log("[put] 큐가 가득 참, 생산자 대기");
            try {
                // RUNNABLE -> WAITING: 락을 반납하고 대기
                // wait(): 해당 스레드는 스레드 대기 집합에서 대기한다.
                wait();
                log("[put] 생산자 깨어남");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        queue.offer(data);
        log("[put] 생산자 데이터 저장, notify() 호출");

        // 대기 스레드, WAIT -> BLOCKED
        // notify(): 스레드 대기 집합에서 대기하는 스레드 중 하나를 깨운다.
        // notify();

        // 모든 대기 스레드, WAIT -> BLOCKED
        notifyAll();
    }

    @Override
    public synchronized String take() {
        while (queue.isEmpty()) {
            log("[take] 큐에 데이터가 없음, 소비자 대기");
            try {
                // RUNNABLE -> WAITING: 락을 반납하고 대기
                wait();
                log("[take] 소비자 깨어남");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        String data = queue.poll();
        log("[take] 소비자 데이터 획득, notify() 호출");

        // 대기 스레드, WAIT -> BLOCKED
        notify();

        // 모든 대기 스레드, WAIT -> BLOCKED
        notifyAll();
        return data;
    }

    @Override
    public String toString() {
        return queue.toString();
    }

}
