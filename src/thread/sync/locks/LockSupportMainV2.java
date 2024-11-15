package thread.sync.locks;

import java.util.concurrent.locks.LockSupport;

import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

public class LockSupportMainV2 {

    public static void main(String[] args) {

        Thread thread1 = new Thread(new ParkTask(), "Thread-1");
        thread1.start();

        // Thread-1 이 park 상태에 빠질 시간을 준다.
        sleep(100);
        log("Thread-1 state: " + thread1.getState()); // TIMED_WAITING

        /*
            12:54:06.069 [ Thread-1] park 시작, 2초 대기
            12:54:06.131 [     main] Thread-1 state: TIMED_WAITING
            12:54:08.076 [ Thread-1] park 종료, state: RUNNABLE
            12:54:08.076 [ Thread-1] 인터럽트 상태: false
         */
    }

    static class ParkTask implements Runnable {
        @Override
        public void run() {
            log("park 시작, 2초 대기");
            LockSupport.parkNanos(2000_000000);
            log("park 종료, state: " + Thread.currentThread().getState());
            log("인터럽트 상태: " + Thread.currentThread().isInterrupted());

            /*
                LockSupport.parkNanos(2000_000000);
                    스레드 상태를 TIMED_WAITING 으로 만든다.
                    인터럽트를 받더라도 바로 멈추지 않으며 인터럽트 상태를 기록할 뿐 실행이 종료되지 않는다.

                Thread.sleep(2000)
                    인터럽트 발생할 경우 InterruptedException 을 발생시킴으로써 바로 멈춘다. 예외처리 필요
             */
        }
    }

}
