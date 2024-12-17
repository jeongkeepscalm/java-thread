package thread.executor.future;

import java.util.concurrent.*;

import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

public class FutureCancelMain {

    private static boolean mayInterruptIfRunning = true;
//    private static boolean mayInterruptIfRunning = false;

    public static void main(String[] args) {

        ExecutorService es = Executors.newFixedThreadPool(1);
        Future<String> future = es.submit(new MyTask());
        log("Future.state: " + future.state()); // RUNNING

        // 일정 시간 후 취소 시도
        sleep(3000);

        // cancel() 호출
        log("future.cancel(" + mayInterruptIfRunning +") 호출");
        boolean cancelResult1 = future.cancel(mayInterruptIfRunning);
        log("Future.state: " + future.state()); // CANCELLED
        log("cancel(" + mayInterruptIfRunning + ") result: " + cancelResult1); // true

        // 결과 확인
        try {
            log("Future result: " + future.get());
        } catch (CancellationException e) {
            log("Future는 이미 취소 되었습니다.");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // Executor 종료
        es.close();
    }

    static class MyTask implements Callable<String> {
        @Override
        public String call() throws Exception {
            try {
                for (int i = 0; i < 10; i++) {
                    log("작업 중: " + i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                log(" 인터럽트 발생");
                return "Interrupted";
            }
            return "Completed";
        }
    }

    /*
        11:07:29.463 [pool-1-thread-1] 작업 중: 0
        11:07:29.463 [     main] Future.state: RUNNING
        11:07:30.478 [pool-1-thread-1] 작업 중: 1
        11:07:31.487 [pool-1-thread-1] 작업 중: 2
        11:07:32.468 [     main] future.cancel(false) 호출
        11:07:32.468 [     main] Future.state: CANCELLED
        11:07:32.476 [     main] cancel(false) result: true
        11:07:32.476 [     main] Future는 이미 취소 되었습니다.
        11:07:32.500 [pool-1-thread-1] 작업 중: 3
        11:07:33.505 [pool-1-thread-1] 작업 중: 4
        11:07:34.513 [pool-1-thread-1] 작업 중: 5
        11:07:35.518 [pool-1-thread-1] 작업 중: 6
        11:07:36.521 [pool-1-thread-1] 작업 중: 7
        11:07:37.529 [pool-1-thread-1] 작업 중: 8
        11:07:38.538 [pool-1-thread-1] 작업 중: 9
     */

}
