package thread.sync;

import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

public class BankMain {

    public static void main(String[] args) throws InterruptedException {

//         BankAccount account = new BankAccountV1(1000);
//         BankAccount account = new BankAccountV2(1000); // 인스턴스 락 존재
//        BankAccount account = new BankAccountV3(1000);
//        BankAccount account = new BankAccountV4(1000);
//        BankAccount account = new BankAccountV5(1000);
        BankAccount account = new BankAccountV6(1000);

        // 한 계좌에서 출금을 2번한다.
        Thread t1 = new Thread(new WithdrawTask(account, 800), "t1");
        Thread t2 = new Thread(new WithdrawTask(account, 800), "t2");
        t1.start();
        t2.start();

        // 검증 완료까지 잠시 대기한다.
        sleep(500);
        log("t1 state: " + t1.getState());
        log("t2 state: " + t2.getState());

        // main 스레드는 join() 을 사용해서 t1 , t2 스레드가 출금을 완료한 이후에 최종 잔액을 확인한다.
        t1.join();
        t2.join();
        log("최종 잔액: " + account.getBalance());

    }

}
