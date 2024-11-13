package thread.test.level1;

import thread.util.ThreadUtils;

import static thread.util.MyLogger.log;

public class ThreadMain {
    public static void main(String[] args) {

        Runnable runnable1 = new PrintAB("A", 1000);
        Runnable runnable2 = new PrintAB("B", 500);

        Thread threadA = new Thread(runnable1, "Thread-A");
        Thread threadB = new Thread(runnable2, "Thread-B");

        threadA.start();
        threadB.start();

    }

    static class PrintAB implements Runnable {

        private final String content;
        private final int sleepMs;

        public PrintAB(String content, int sleepMs) {
            this.content = content;
            this.sleepMs = sleepMs;
        }

        @Override
        public void run() {
            while (true) {
                log(content);
                ThreadUtils.sleep(sleepMs);
//                try {
//                    Thread.sleep(sleepMs);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
            }
        }


    }
}