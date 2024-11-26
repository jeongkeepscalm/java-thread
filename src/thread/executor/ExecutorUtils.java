package thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

import static thread.util.MyLogger.log;

public abstract class ExecutorUtils {

    public static void printState(ExecutorService executorService) {
        if (executorService instanceof ThreadPoolExecutor poolExecutor) {
            int poolSize = poolExecutor.getPoolSize();
            int activeCount = poolExecutor.getActiveCount();
            int queuedTasks = poolExecutor.getQueue().size();
            long completedTask = poolExecutor.getCompletedTaskCount();
            log("[poolSize=" + poolSize + ", activeCount=" + activeCount + ", queuedTasks=" + queuedTasks + ", completedTasks=" + completedTask + "]");
            /*
                poolSize:       풀에서 관리되는 스레드 숫자
                activeCount:    작업을 수행하는 스레드 숫자
                queuedTasks:    큐에서 대기중인 작업의 숫자
                completedTask:  완료된 작업의 숫자
             */
        } else {
            log(executorService);
        }
    }

}
