package thread.start;

import static thread.util.MyLogger.log;

public class RunnableMainV4 {

    public static void main(String[] args) {
        log("main() start");
        Thread thread = new Thread(() -> log("람다를 활용한 Runnable"));
        thread.start();
        log("main() end");
    }

}
