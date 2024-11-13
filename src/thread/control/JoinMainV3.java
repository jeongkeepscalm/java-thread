package thread.control;

import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

/**
 * join() 을 호출하는 스레드는 대상 스레드가 TERMINATED 상태가 될 때 까지 대기한다.
 * 다른 스레드가 완료될 때 까지 무기한 기다리는 단점 존재
 */
public class JoinMainV3 {

    public static void main(String[] args) throws InterruptedException {
        log("Start");
        SumTask task1 = new SumTask(1, 50);
        SumTask task2 = new SumTask(51, 100);
        Thread thread1 = new Thread(task1, "thread-1");
        Thread thread2 = new Thread(task2, "thread-2");
        thread1.start();
        thread2.start();

        // 스레드가 종료될 때 까지 대기
        log("join() - main 스레드가 thread1, thread2 종료까지 대기한다. < main thread Waiting >");
        thread1.join(); // InterruptedException 예외 던짐
        thread2.join(); // InterruptedException 예외 던짐
        log("main 스레드 대기 완료");

        log("task1.result = " + task1.sum );
        log("task2.result = " + task2.sum);

        int sumAll = task1.sum + task2.sum;
        log("task1 + task2 = " + sumAll);
        log("End");

        /*
            17:10:03.223 [     main] Start
            17:10:03.235 [     main] join() - main 스레드가 thread1, thread2 종료까지 대기
            17:10:03.235 [ thread-1] 작업 시작
            17:10:03.235 [ thread-2] 작업 시작
            17:10:05.256 [ thread-1] 작업 완료: sum= 1275
            17:10:05.256 [ thread-2] 작업 완료: sum= 3775
            17:10:05.257 [     main] main 스레드 대기 완료
            17:10:05.258 [     main] task1.result = 1275
            17:10:05.259 [     main] task2.result = 3775
            17:10:05.260 [     main] task1 + task2 = 5050
            17:10:05.260 [     main] End
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
