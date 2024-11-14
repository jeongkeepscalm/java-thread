package thread.control.printer;

import java.util.InputMismatchException;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;

import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

/*
    main 스레드:       사용자 입력을 받는다.
    printer 스레드:    사용자의 입력을 출력한다.
 */
public class MyPrinterV1 {

    public static void main(String[] args) {
        Printer printer = new Printer();
        Thread printerThread = new Thread(printer, "printer");
        printerThread.start();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            log("프린트할 문서를 입력하세요. 종료 (q): ");
            String input = scanner.nextLine();
            if ("q".equals(input)) {
                printer.work = false;
                break;
            }
            printer.addJob(input);
        }

        /*
            문제점: q 입력 시 바로 종료되지 않는다.

            13:08:37.942 [     main] 프린트할 문서를 입력하세요. 종료 (q):
            13:08:38.894 [  printer] 출력 완료: a
            13:08:38.895 [  printer] 출력 시작: d, 대기 문서:[f, s]
            q
            13:08:41.910 [  printer] 출력 완료: d
            13:08:41.910 [  printer] 프린터 종료
         */
    }

    static class Printer implements Runnable {
        /*
            volatile
                여러 스레드가 동시에 접근하는 변수에 붙여주어야 안전하다.
            ConcurrentLinkedQueue
                여러 스레드가 동시에 접근하는 경우, 컬렉션 프레임워크가 제공하는 일반적인 자료구조를 사용하면 안전하지 않다.
                여러 스레드가 동시에 접근하는 경우 동시성을 지원하는 동시성 컬렉션을 사용해야 한다.
         */
        volatile boolean work = true;
        Queue<String> jobQueue = new ConcurrentLinkedQueue<>();

        @Override
        public void run() {
            while (work) {
                if (jobQueue.isEmpty()) {
                    continue;
                }
                String job = jobQueue.poll();
                log("출력 시작: " + job + ", 대기 문서:" + jobQueue);
                sleep(3000);
                log("출력 완료: " + job);
            }
            log("프린터 종료");
        }

        void addJob(String input) {
            jobQueue.offer(input);
        }

    }

}
