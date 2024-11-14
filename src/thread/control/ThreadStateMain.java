package thread.control;

import thread.util.ThreadUtils;

import static thread.util.MyLogger.log;

public class ThreadStateMain {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new MyRunnable(), "myThread");
        log("myThread.state1 = " + thread.getState()); // NEW
        log("myThread.start()");
        thread.start();
        Thread.sleep(1000);
        log("myThread.state3 = " + thread.getState()); // TIMED_WAITING
        Thread.sleep(4000);
        log("myThread.state5 = " + thread.getState()); // TERMINATED
        log("end");
    }

    public static class MyRunnable implements Runnable {
        @Override
        public void run() {
            log("start");
            log("myThread.state2 = " + Thread.currentThread().getState()); // RUNNABLE
            log("sleep() start");
            ThreadUtils.sleep(3000);
            log("sleep() end");
            log("myThread.state4 = " + Thread.currentThread().getState()); // RUNNABLE
            log("end");
        }
    }

}
