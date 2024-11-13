package thread.control;

import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

/**
 * 특정 스레드를 기다리게 하는 가장 간단한 방법은 sleep() 을 사용하는 것이다.
 * 하지만 thread-1, thread-2 의 수행시간이 달라지는 경우에는 정확한 타이밍을 맞추기 어렵다.
 */
public class JoinMainV2 {

    public static void main(String[] args) {
        log("Start");
        SumTask task1 = new SumTask(1, 50);
        SumTask task2 = new SumTask(51, 100);
        Thread thread1 = new Thread(task1, "thread-1");
        Thread thread2 = new Thread(task2, "thread-2");
        thread1.start();
        thread2.start();

        // 정확한 타이밍을 맞추어 기다리기 어려움
        log("main 스레드 sleep()");
        sleep(3000);
        log("main 스레드 깨어남");

        log("task1.result = " + task1.sum );
        log("task2.result = " + task2.sum);

        int sumAll = task1.sum + task2.sum;
        log("task1 + task2 = " + sumAll);
        log("End");

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
