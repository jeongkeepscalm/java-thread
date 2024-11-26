package thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static thread.executor.ExecutorUtils.printState;
import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

public class ExecutorBasicMain {

    public static void main(String[] args) {

        ExecutorService executorService =
                new ThreadPoolExecutor(2, 2, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        /*
            corePoolSize:
                스레드 풀에서 관리되는 기본 스레드의 수
                corePoolSize 에 지정한 수 만큼 스레드를 스레드 풀에 만든다.

            maximumPoolSize:
                스레드 풀에서 관리되는 최대 스레드 수

            keepAliveTime, TimeUnit unit:
                기본 스레드 수를 초과해서 만들어진 스레드가 생존할 수 있는 대기 시간이다.
                이 시간 동안 처리할 작업이 없다면 초과 스레드는 제거된다.

            BlockingQueue workQueue:
                작업을 보관할 블로킹 큐
         */

        log("== 초기 상태 ==");
        printState(executorService);
        executorService.execute(new RunnableTask("taskA"));
        executorService.execute(new RunnableTask("taskB"));
        executorService.execute(new RunnableTask("taskC"));
        executorService.execute(new RunnableTask("taskD"));

        log("== 작업 수행 중 ==");
        printState(executorService);

        sleep(3000);
        log("== 작업 수행 완료 ==");
        printState(executorService);
        executorService.close();

        log("== shutdown 완료 ==");
        printState(executorService);

        /*
            09:55:21.464 [     main] == 초기 상태 ==
            09:55:21.495 [     main] [poolSize=0, activeCount=0, queuedTasks=0, completedTasks=0]
            09:55:21.511 [     main] == 작업 수행 중 ==
            09:55:21.511 [     main] [poolSize=2, activeCount=2, queuedTasks=2, completedTasks=0]
            09:55:21.511 [pool-1-thread-1] taskA 시작
            09:55:21.511 [pool-1-thread-2] taskB 시작
            09:55:22.521 [pool-1-thread-1] taskA 완료
            09:55:22.521 [pool-1-thread-2] taskB 완료
            09:55:22.522 [pool-1-thread-1] taskC 시작
            09:55:22.523 [pool-1-thread-2] taskD 시작
            09:55:23.535 [pool-1-thread-1] taskC 완료
            09:55:23.535 [pool-1-thread-2] taskD 완료
            09:55:24.520 [     main] == 작업 수행 완료 ==
            09:55:24.521 [     main] [poolSize=2, activeCount=0, queuedTasks=0, completedTasks=4]
            09:55:24.522 [     main] == shutdown 완료 ==
            09:55:24.523 [     main] [poolSize=0, activeCount=0, queuedTasks=0, completedTasks=4]
         */

    }

}
