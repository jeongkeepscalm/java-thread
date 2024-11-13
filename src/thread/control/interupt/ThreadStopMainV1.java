package thread.control.interupt;

import java.awt.desktop.ScreenSleepEvent;

import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

public class ThreadStopMainV1 {

    public static void main(String[] args) {

        MyTask myTask = new MyTask();
        Thread thread = new Thread(myTask, "work");
        thread.start();

        sleep(4000);
        log("작업 중단 지시 runFlag=false");
        myTask.runFlag = false;

        /*
            17:55:21.971 [     work] 작업중
            17:55:24.978 [     work] 작업중
            17:55:25.964 [     main] 작업 중단 지시 runFlag=false
            17:55:27.990 [     work] 자원 정리
            17:55:27.991 [     work] 작업 종료

            문제점
                작업 중단 지시 2초 정도 이후에 자원을 정리하고 작업을 종료한다.
                main 스레드가 runFlag 를 false 로 변경해도, work 스레드는 sleep(3000) 을 통해 3초간 잠들어 있기 때문이다.

            어떻게 하면 sleep() 처럼 스레드가 대기하는 상태에서 스레드를 깨우고, 작업도 빨리 종료할 수 있을까?
            =>
         */

    }

    static class MyTask implements Runnable {

        volatile boolean runFlag = true;

        @Override
        public void run() {
            while (runFlag) {
                log("작업중");
                sleep(3000);
            }
            log("자원 정리");
            log("작업 종료");
        }
    }
}
