package thread.start;

import static thread.util.MyLogger.log;

public class RunnableMainV2 {

    public static void main(String[] args) {
        log("main() start");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                log("익명 클래스 활용 Runnable");
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        log("main() end");
    }

}
