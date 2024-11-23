package thread.cas.spinlock;

import static thread.util.MyLogger.log;

public class SpinLockMain {
    public static void main(String[] args) {

//        spinLock spinLock = new spinLock();
        SpinLock spinLock = new SpinLock();

        Runnable task = new Runnable() {

            @Override
            public void run() {
                spinLock.lock();
                try {
                    // critical section
                    log("business logic");
                    // sleep(1); // 오래 걸리는 로직에서 스핀 락 사용 x
                    /*
                        - 락을 기다리는 스레드가 block, waiting 상태에 빠지지 않지만
                        runnable 상태로 락을 획득할 때까지 while 문을 반복하는 문제 존재.
                        - cpu 자원 낭비: 락을 기다리는 스레드가 cpu 를 계속 사용하면서 대기
                     */
                } finally {
                    spinLock.unlock();
                }
            }
        };
        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");
        t1.start();
        t2.start();
    }
}
