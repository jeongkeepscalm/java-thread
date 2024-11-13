package thread.control.interupt;

import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

public class ThreadStopMainV2 {

    public static void main(String[] args) {

        MyTask myTask = new MyTask();
        Thread thread = new Thread(myTask, "work");
        thread.start();

        sleep(4000);
        log("작업 중단 지시 thread.interrupt()");
        thread.interrupt();
        log("work 스레드 인터럽트 상태1 = " + thread.isInterrupted());

        /*
            18:04:31.477 [     work] 작업 중
            18:04:34.494 [     work] 작업 중
            18:04:35.475 [     main] 작업 중단 지시 thread.interrupt()
            18:04:35.487 [     main] work 스레드 인터럽트 상태1 = true
            18:04:35.487 [     work] work 스레드 인터럽트 상태2 = false
            18:04:35.488 [     work] interrupt message=sleep interrupted
            18:04:35.489 [     work] state=RUNNABLE
            18:04:35.490 [     work] 자원 정리
            18:04:35.490 [     work] 작업 종료
         */
    }

    static class MyTask implements Runnable {

        @Override
        public void run() {
            try {
                while (true) {
                    log("작업 중");
                    Thread.sleep(3000);
                }
            } catch (InterruptedException e) {
                log("work 스레드 인터럽트 상태2 = " + Thread.currentThread().isInterrupted());
                log("interrupt message=" + e.getMessage());
                log("state=" + Thread.currentThread().getState());
            }
            log("자원 정리");
            log("작업 종료");
        }
    }

}
