package thread.util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MyLogger {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    public static void log(Object object) {
        String time = LocalTime.now().format(FORMATTER);
        System.out.printf("%s [%9s] %s\n", time, Thread.currentThread().getName(), object);
    }

}
