package thread.control.interupt;

import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

public class ThreadStopMainV3 {

    public static void main(String[] args) {

        MyTask myTask = new MyTask();
        Thread myTaskThread = new Thread(myTask, "work");
        myTaskThread.start();

        sleep(100);
        log("작업 중단 지시 - myTaskThread.interrupt()");
        myTaskThread.interrupt(); // myTaskThread 작업 즉각 중단
        log("work 스레드 인터럽트 상태1 = " + myTaskThread.isInterrupted());

        /*
            10:41:13.651 [     work] 작업중 ...
            10:41:13.651 [     work] 작업중
            10:41:13.652 [     main] 작업 중단 지시 - myTaskThread.interrupt()
            10:41:13.652 [     work] 작업중
            10:41:13.652 [     main] work 스레드 인터럽트 상태1 = true
            10:41:13.652 [     work] work 스레드 인터럽트 상태2 = true
            10:41:13.652 [     work] 자원 정리 시도
            10:41:13.652 [     work] 자원 정리 실패 - 자원 정리 중 인터럽트 발생
            10:41:13.652 [     work] work 스레드 인터럽트 상태3 = false
            10:41:13.652 [     work] 작업 종료
         */

    }

    static class MyTask implements Runnable {

        @Override
        public void run() {

            while (!Thread.currentThread().isInterrupted()) {
                log("작업중");
            }
            log("work 스레드 인터럽트 상태2 = " + Thread.currentThread().isInterrupted());

            try {
                log("자원 정리 시도");
                Thread.sleep(1000); // 인터럽트 상태가 ture 일 경우 sleep() 을 호출한다면, 해당 코드에서 인터럽트 예외가 발생하게 된다.
                log("자원 정리 완료");
            } catch (InterruptedException e) {
                log("자원 정리 실패 - 자원 정리 중 인터럽트 발생");
                log("work 스레드 인터럽트 상태3 = " + Thread.currentThread().isInterrupted());
                // 스레드의 인터럽트 상태를 정상적으로 돌리지 않으면 계속 인터럽트가 발생하기에 내부에서 false 로 변환한다.
            }
            log("작업 종료");
        }

    }

}
