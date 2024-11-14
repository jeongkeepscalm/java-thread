package thread.control.interupt;

import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

public class ThreadStopMainV4 {

    public static void main(String[] args) {

        MyTask myTask = new MyTask();
        Thread myTaskThread = new Thread(myTask, "work");
        myTaskThread.start();

        sleep(100);
        log("작업 중단 지시 - myTaskThread.interrupt()");
        myTaskThread.interrupt();
        log("work 스레드 인터럽트 상태1 = " + myTaskThread.isInterrupted());

    }

    static class MyTask implements Runnable {

        @Override
        public void run() {

            /*
                isInterrupted(): 특정 스레드의 상태를 변경하지 않고 확인만 한다.
                Thread.interrupted()
                    현재 스레드의 인터럽트 상태를 확인하고, 인터럽트 상태를 초기화한다.
                    인터럽트가 true 일 경우 false 로 초기화
             */

            while (!Thread.interrupted()) {
                log("작업중");
            }
            log("work 스레드 인터럽트 상태2 = " + Thread.currentThread().isInterrupted());

            try {
                log("자원 정리 시도");
                Thread.sleep(1000);
                // 인터럽트 상태가 ture 일 경우 sleep() 을 호출한다면, 해당 코드에서 인터럽트 예외가 발생하게 된다.
                //
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
