package thread.start;

import static thread.start.MyLogger.*;

public class DaemonThreadMain {

    public static void main(String[] args) {
        log("main thread start");
        DaemonThread daemonThread = new DaemonThread();
//        daemonThread.setDaemon(true);
        daemonThread.start();
        log("main thread end");
    }


    static class DaemonThread extends Thread {
        @Override
        public void run() {
            log("daemon thread start");
            try {
                Thread.sleep(10000); // 10초간 실행
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log("daemon thread end");
        }
    }
}
