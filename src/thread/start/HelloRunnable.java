package thread.start;

import static thread.start.MyLogger.log;

public class HelloRunnable implements Runnable {
    @Override
    public void run() {
        log("run()");
    }
}
