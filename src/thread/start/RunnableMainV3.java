package thread.start;

import static thread.util.MyLogger.log;

public class RunnableMainV3 {

    public static void main(String[] args) {
        log("main() start");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                log("변수 없는 익명 클래스 활용 Runnable");
            }
        });
        thread.start();
        log("main() end");
    }

}
