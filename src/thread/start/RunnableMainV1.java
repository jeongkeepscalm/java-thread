package thread.start;

import static thread.start.MyLogger.log;

public class RunnableMainV1 {

    public static void main(String[] args) {
        log("main() start");
        MyRunnableV1 myRunnableV1 = new MyRunnableV1();
        Thread thread = new Thread(myRunnableV1);
        thread.start();
        log("main() end");
    }

    // 중첩 클래스
    static class MyRunnableV1 implements Runnable {
        @Override
        public void run() {
            log("중첩 클래스 활용 Runnable");
        }
    }

}
