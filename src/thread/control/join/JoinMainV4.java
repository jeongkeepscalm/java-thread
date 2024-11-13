package thread.control.join;

import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

/**
 * join(ms) 을 호출하는 스레드는 대상 스레드가 ms 동안 대기한다.
 * ms 이전에 해당 스레드가 종료되면 ms 동안 기다리지 않고 호출한 스레드가 실행된다.
 */
public class JoinMainV4 {

    public static void main(String[] args) throws InterruptedException {
        log("Start");
        SumTask task1 = new SumTask(1, 50);
        Thread thread1 = new Thread(task1, "thread-1");
        thread1.start();
        // 스레드가 종료될 때 까지 대기
        log("join(5000) - main 스레드가 최대 5초 동안 thread1 종료까지 대기한다.");
        thread1.join(5000);
        log("main 스레드 대기 완료");
        log("task1.sum = " + task1.sum);
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
