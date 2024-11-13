package thread.test.level1;

public class CounterRunnableMain {
    public static void main(String[] args) {
        CounterRunnable counterRunnable = new CounterRunnable();
        Thread thread = new Thread(counterRunnable, "counter");
        thread.start();
    }
}
