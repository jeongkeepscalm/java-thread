package thread.test.level3;

import static thread.util.MyLogger.log;

/*
    실행 결과 예측
 */
public class SyncTest2BadMain {

    public static void main(String[] args) throws InterruptedException {

        MyCounter myCounter = new MyCounter();
        Runnable task = new Runnable() {
            @Override
            public void run() {
                myCounter.count();
            }
        };
        Thread thread1 = new Thread(task, "Thread-1");
        Thread thread2 = new Thread(task, "Thread-2");
        thread1.start();
        thread2.start();
    }

    static class MyCounter {
        public void count() {
            int localValue = 0;
            for (int i = 0; i < 1000; i++) {
                localValue = localValue + 1;
            }
            log("결과: " + localValue);
        }
    }

    /*
        18:32:10.673 [ Thread-1] 결과: 1000
        18:32:10.673 [ Thread-2] 결과: 1000

        localValue 는 지역 변수
        지역 변수는 절대로! 다른 스레드와 공유되지 않는다.

     */

}
