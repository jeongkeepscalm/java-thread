package thread.executor.future;

import thread.executor.CallableTask;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static thread.util.MyLogger.log;

public class InvokeAnyMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService es = Executors.newFixedThreadPool(10);

        CallableTask task1 = new CallableTask("task1", 1000);
        CallableTask task2 = new CallableTask("task2", 2000);
        CallableTask task3 = new CallableTask("task3", 3000);

        List<CallableTask> tasks = List.of(task1, task2, task3);

        /**
            invokeAny() 는 한 번에 여러 작업을 제출하고, 가장 먼저 완료된 작업의 결과를 반환한다.
            이때 완료되지 않은 나머지 작업은 인터럽트를 통해 취소한다.
         */
        Integer value = es.invokeAny(tasks);
        log("value = " + value);
        es.close();

        /*
            13:08:26.506 [pool-1-thread-3] task3 실행
            13:08:26.506 [pool-1-thread-2] task2 실행
            13:08:26.506 [pool-1-thread-1] task1 실행
            13:08:27.528 [pool-1-thread-1] task1 완료, return = 1000
            13:08:27.530 [     main] value = 1000
            13:08:27.530 [pool-1-thread-2] 인터럽트 발생, sleep interrupted
            13:08:27.530 [pool-1-thread-3] 인터럽트 발생, sleep interrupted
         */

    }

}
