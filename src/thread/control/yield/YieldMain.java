package thread.control.yield;

import static thread.util.ThreadUtils.sleep;

/*
    Thread.yield()
        현재 실행 중인 스레드가 자발적으로 CPU 를 양보하여 다른 스레드가 실행될 수 있도록 한다.
        yield() 메서드를 호출한 스레드는 RUNNABLE 상태를 유지하면서 CPU 를 양보한다.
        yield() 는 RUNNABLE 상태를 유지하기 때문에, 쉽게 이야기해서 양보할 사람이 없다면 본인 스레드가 계속 실행될 수 있다.
 */
public class YieldMain {

    static final int THREAD_COUNT = 1000;

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(new MyRunnable());
            thread.start();
        }
    }

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " - " + i);
            }

//            sleep(1);
            Thread.yield();

        }
    }
}
