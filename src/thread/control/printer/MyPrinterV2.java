package thread.control.printer;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

/*
    main 스레드:       사용자 입력을 받는다.
    printer 스레드:    사용자의 입력을 출력한다.
 */
public class MyPrinterV2 {

    public static void main(String[] args) {
        Printer printer = new Printer();
        Thread printerThread = new Thread(printer, "printer");
        printerThread.start();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            log("프린트할 문서를 입력하세요. 종료 (q): ");
            String input = scanner.nextLine();
            if ("q".equals(input)) {
                printerThread.interrupt();
                break;
            }
            printer.addJob(input);
        }

    }

    static class Printer implements Runnable {
        Queue<String> jobQueue = new ConcurrentLinkedQueue<>();

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                if (jobQueue.isEmpty()) continue;
                try {
                    String job = jobQueue.poll();
                    log("출력 시작: " + job + ", 대기 문서:" + jobQueue);
                    Thread.sleep(3000);
                    log("출력 완료: " + job);
                } catch (InterruptedException e) {
                    log("인터럽트!");
                    break;
                }
            }
            log("프린터 종료");
        }

        void addJob(String input) {
            jobQueue.offer(input);
        }

    }

}
