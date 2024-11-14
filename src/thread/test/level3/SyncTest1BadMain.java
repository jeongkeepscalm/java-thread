package thread.test.level3;

/*
    기대하는 실행 결과
    결과: 20000
 */
public class SyncTest1BadMain {

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        Runnable task = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    counter.increment();
                }
            }
        };
        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);
        thread1.start();
        thread2.start();

        // join() 을 호출하는 main 스레드는 해당 스레드들의 상태가 terminated 가 될 때까지 기다린다.
        thread1.join();
        thread2.join();
        System.out.println("결과: " + counter.getCount());
        /*  
            결과: 20000 이하

            공유 변수 경합 상태에 의해 두 스레드가 계산한 값을 거의 동시에 count 에 저장하게 되면,
            실제로는 두 번 증가해야 할 count 가 한 번만 증가하게 된다.
         */
    }

    static class Counter {
        private int count = 0;
        public void increment() {
            count = count + 1;
        }
        public int getCount() {
            return count;
        }
    }

}
