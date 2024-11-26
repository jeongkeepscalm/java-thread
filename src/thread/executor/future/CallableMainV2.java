package thread.executor.future;

import java.util.Random;
import java.util.concurrent.*;

import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

public class CallableMainV2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService es = Executors.newFixedThreadPool(1);

        log("submit() 호출");
        Future<Integer> future = es.submit(new MyCallable());
        log("future 즉시 반환, future = " + future);

        log("future.get() [블로킹] 메서드 호출 시작 -> main 스레드 WAITING");
        Integer result = future.get();
        log("future.get() [블로킹] 메서드 호출 완료 -> , main 스레드 RUNNABLE");

        log("result value = " + result);
        log("future 완료, future = " + future);
        es.close();

        /*
            10:44:44.750 [     main] submit() 호출
            10:44:44.765 [pool-1-thread-1] Callable 시작
            10:44:44.765 [     main] future 즉시 반환, future = java.util.concurrent.FutureTask@6576fe71[Not completed, task = thread.executor.future.CallableMainV2$MyCallable@7eda2dbb]
            10:44:44.765 [     main] future.get() [블로킹] 메서드 호출 시작 -> main 스레드 WAITING
            10:44:46.775 [pool-1-thread-1] create value = 0
            10:44:46.776 [pool-1-thread-1] Callable 완료
            10:44:46.777 [     main] future.get() [블로킹] 메서드 호출 완료 -> , main 스레드 RUNNABLE
            10:44:46.778 [     main] result value = 0
            10:44:46.779 [     main] future 완료, future = java.util.concurrent.FutureTask@6576fe71[Completed normally]
         */
    }

    /**
     * Callable:
     *      Runnable 인터페이스와는 다르게,
     *      반환값 존재
     *      예외 처리 가능
     */
    static class MyCallable implements Callable<Integer> {

        @Override
        public Integer call() {
            log("Callable 시작");
            sleep(2000);
            int value = new Random().nextInt(10);
            log("create value = " + value);
            log("Callable 완료");
            return value;
        }

    }

}
