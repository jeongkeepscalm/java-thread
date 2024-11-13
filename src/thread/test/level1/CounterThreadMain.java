package thread.test.level1;

public class CounterThreadMain {

    public static void main(String[] args) {
        CounterThread counterThread = new CounterThread();
        counterThread.start();
    }
}
