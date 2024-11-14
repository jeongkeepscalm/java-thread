package thread._volatile;

import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

public class VolatileCountMain {

    public static void main(String[] args) {

        MyTask myTask = new MyTask();
        Thread t = new Thread(myTask, "work");
        t.start();

        sleep(1000);
        myTask.flag = false;
        log("flag = " + myTask.flag + ", count = " + myTask.count + " in main");

        /*
            volatile x (메모리 가시성 확인)
                15:25:29.547 [     work] flag = true, count = 100000000 in while()
                15:25:29.777 [     work] flag = true, count = 200000000 in while()
                15:25:29.986 [     work] flag = true, count = 300000000 in while()
                15:25:30.173 [     work] flag = true, count = 400000000 in while()
                15:25:30.309 [     main] flag = false, count = 472079158 in main
                15:25:30.352 [     work] flag = true, count = 500000000 in while()
                15:25:30.352 [     work] flag = false, count = 500000000 종료

            volatile o
                15:29:23.806 [     work] flag = true, count = 100000000 in while()
                15:29:23.985 [     main] flag = false, count = 123342843 in main
                15:29:23.985 [     work] flag = false, count = 123342843 종료
         */

    }

    static class MyTask implements Runnable {

        boolean flag = true;
        long count;
        // volatile boolean flag = true;
        // volatile long count;

        @Override
        public void run() {
            while (flag) {
                count++;
                // 1억번에 한 번씩 출력한다.
                if (count % 100_000_000 == 0) {
                    log("flag = " + flag + ", count = " + count + " in while()");
                }
            }
            log("flag = " + flag + ", count = " + count + " 종료");
        }

    }

}
