package thread.test.level2;

import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;
/*
    문제1의 코드를 변경해서 전체 실행 시간을 3초로 앞당겨보자. 실행 결과를 참고하자.
 */
public class JoinTest2Main {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new MyTask(), "t1");
        Thread t2 = new Thread(new MyTask(), "t2");
        Thread t3 = new Thread(new MyTask(), "t3");
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println("모든 스레드 실행 완료");
    }

    static class MyTask implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i <= 3; i++) {
                log(i);
                sleep(1000);
            }
        }
    }
}