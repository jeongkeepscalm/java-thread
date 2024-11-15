package thread.sync.locks;

import java.util.concurrent.locks.LockSupport;

import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

public class LockSupportMainV1 {

    public static void main(String[] args) {

        Thread thread1 = new Thread(new ParkTask(), "Thread-1");
        thread1.start();

        // Thread-1 이 park 상태에 빠질 시간을 준다.
        sleep(100);
        log("Thread-1 state: " + thread1.getState());

        log("main -> unpark(Thread-1)");
        LockSupport.unpark(thread1); // 1. 외부 스레드(main)에서 Thread1 unpark 사용
        //thread1.interrupt(); // 2. interrupt() 사용

        /*
            11:57:12.184 [ Thread-1] park 시작
            11:57:12.279 [     main] Thread-1 state: WAITING
            11:57:12.279 [     main] main -> unpark(Thread-1)
            11:57:12.279 [ Thread-1] park 종료, state: RUNNABLE
            11:57:12.279 [ Thread-1] 인터럽트 상태: false
         */
    }

    static class ParkTask implements Runnable {
        @Override
        public void run() {
            log("park 시작");
            LockSupport.park();
            log("park 종료, state: " + Thread.currentThread().getState());
            log("인터럽트 상태: " + Thread.currentThread().isInterrupted());
        }
    }

}
