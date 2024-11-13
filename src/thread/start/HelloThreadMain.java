package thread.start;

public class HelloThreadMain {

    public static void main(String[] args) {

        // Thread.currentThread(): 현재 이 코드를 실행하는 스레드
        System.out.println(Thread.currentThread().getName() + ": main() start");

        HelloThread helloThread = new HelloThread();
        System.out.println(Thread.currentThread().getName() + ": start() 호출 전");
        helloThread.start(); // 호출 시 스택영역에 할당되면서 실행된다. (Thread-0 스레드)
        System.out.println(Thread.currentThread().getName() + ": start() 호출 후");

        System.out.println(Thread.currentThread().getName() + ": main() end");

        /*
            main: main() start
            main: start() 호출 전
            main: start() 호출 후
            main: main() end
            Thread-0: run()
                Thread-0 스레드가 run() 호출(main 스레드가 run() 호출 x)

            helloThread.start(); 호출 시 main 스레드와 Thread-0 스레드가 동시에 실행된다.
            스레드 간 실행 순서는 보장하지 않는다.
         */

    }

}
