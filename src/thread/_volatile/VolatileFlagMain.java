package thread._volatile;

import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

public class VolatileFlagMain {

    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        Thread myThread = new Thread(myTask, "myTask");
        log("runFlag= " + myTask.runFlag);
        myThread.start();

        sleep(1000);
        log("runFlag: true -> false");
        myTask.runFlag = false;
        log("runFlag= " + myTask.runFlag);
        log("메인 스레드 종료");
    }

    static class MyTask implements Runnable {
        volatile boolean runFlag = true;
        @Override
        public void run() {
            log("task 시작");
            while (runFlag) {
                log("실행중");
            }
            log("task 종료");
        }
    }

}
