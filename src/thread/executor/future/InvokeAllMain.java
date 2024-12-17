package thread.executor.future;

import thread.executor.CallableTask;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static thread.util.MyLogger.log;

public class InvokeAllMain {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService es = Executors.newFixedThreadPool(10);

        CallableTask task1 = new CallableTask("task1", 1000);
        CallableTask task2 = new CallableTask("task2", 2000);
        CallableTask task3 = new CallableTask("task3", 3000);

        List<CallableTask> tasks = List.of(task1, task2, task3);

        /** invokeAll(): 한 번에 여러 작업을 제출하고, 모든 작업이 완료될 때 까지 기다린다. */
        List<Future<Integer>> futures = es.invokeAll(tasks);
        for (Future<Integer> future : futures) {
            Integer value = future.get();
            log("value = " + value);
        }
        es.close();
        /*
            13:10:32.909 [pool-1-thread-3] task3 실행
            13:10:32.909 [pool-1-thread-2] task2 실행
            13:10:32.909 [pool-1-thread-1] task1 실행
            13:10:33.929 [pool-1-thread-1] task1 완료, return = 1000
            13:10:34.917 [pool-1-thread-2] task2 완료, return = 2000
            13:10:35.916 [pool-1-thread-3] task3 완료, return = 3000
            13:10:35.917 [     main] value = 1000
            13:10:35.918 [     main] value = 2000
            13:10:35.918 [     main] value = 3000
         */
    }

}
