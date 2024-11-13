package thread.start;

import thread.util.MyLogger;

public class MyLoggerMain {
    public static void main(String[] args) {
        MyLogger.log("hello thread");
        MyLogger.log(12345);
    }
}
