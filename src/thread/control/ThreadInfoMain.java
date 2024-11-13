package thread.control;

import thread.start.HelloRunnable;

import static thread.util.MyLogger.log;

public class ThreadInfoMain {
    public static void main(String[] args) {

        // main 스레드
        Thread mainThread = Thread.currentThread();
        log("mainThread = " + mainThread);
        log("mainThread.threadId() = " + mainThread.threadId());        // pk
        log("mainThread.getName() = " + mainThread.getName());          // 이름 중복 가능
        log("mainThread.getPriority() = " + mainThread.getPriority());  // 1 ~ 10(우선순위 높음. 기본값 5)
        log("mainThread.getThreadGroup() = " + mainThread.getThreadGroup());
        log("mainThread.getState() = " + mainThread.getState());

        System.out.println();

        // myThread 스레드
        Thread myThread = new Thread(new HelloRunnable(), "myThread");
        log("myThread = " + myThread);
        log("myThread.threadId() = " + myThread.threadId());
        log("myThread.getName() = " + myThread.getName());
        log("myThread.getPriority() = " + myThread.getPriority());
        log("myThread.getThreadGroup() = " + myThread.getThreadGroup());
        log("myThread.getState() = " + myThread.getState());
        myThread.start();

        /*
            getState()
                NEW: 스레드가 아직 시작되지 않은 상태이다.
                RUNNABLE: 스레드가 실행 중이거나 실행될 준비가 된 상태이다.
                    BLOCKED: 스레드가 동기화 락을 기다리는 상태이다.
                    WAITING: 스레드가 다른 스레드의 특정 작업이 완료되기를 기다리는 상태이다.
                    TIMED_WAITING: 일정 시간 동안 기다리는 상태이다.
                TERMINATED: 스레드가 실행을 마친 상태이다.
         */
    }
}
