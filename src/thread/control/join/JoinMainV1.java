package thread.control.join;

import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

public class JoinMainV1 {

    public static void main(String[] args) {
        log("Start");
        SumTask task1 = new SumTask(1, 50);
        SumTask task2 = new SumTask(51, 100);
        Thread thread1 = new Thread(task1, "thread-1");
        Thread thread2 = new Thread(task2, "thread-2");
        thread1.start();
        thread2.start();

        log("task1.result = " + task1.sum );
        log("task2.result = " + task2.sum);

        int sumAll = task1.sum + task2.sum;
        log("task1 + task2 = " + sumAll);
        log("End");

        /*
            16:59:47.546 [     main] Start
            16:59:47.556 [ thread-1] 작업 시작
            16:59:47.556 [ thread-2] 작업 시작
            16:59:47.560 [     main] task1.result = 0
            16:59:47.560 [     main] task2.result = 0
            16:59:47.561 [     main] task1 + task2 = 0
            16:59:47.561 [     main] End
            16:59:49.566 [ thread-1] 작업 완료: sum= 1275
            16:59:49.566 [ thread-2] 작업 완료: sum= 3775
         */
    }

    static class SumTask implements Runnable {

        private final int FIRST;
        private final int LAST;
        int sum;

        public SumTask(int first, int last) {
            this.FIRST = first;
            this.LAST = last;
            this.sum = 0;
        }

        @Override
        public void run() {
            log("작업 시작");
            sleep(2000);
            for (int i = FIRST; i <= LAST; i++) {
                sum += i;
            }
            log("작업 완료: sum= " + sum);
        }

    }

}
