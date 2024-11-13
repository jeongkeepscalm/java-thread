package thread.test.level1;

import static thread.start.MyLogger.log;

public class CounterRunnableMain2 {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 5; i++) {
                    try {
                        log("value: " + i);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }, "counter");
        thread.start();
    }
}
