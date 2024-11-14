package thread.sync;

import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

public class BankMain {

    public static void main(String[] args) throws InterruptedException {

        // BankAccount account = new BankAccountV1(1000);
        // BankAccount account = new BankAccountV2(1000); // 인스턴스 락 존재
        BankAccount account = new BankAccountV3(1000);

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

        /*
            synchronized 키워드로 인한 동시성 문제 해결
            
            17:15:38.879 [       t1] 거래 시작: BankAccountV2
            17:15:38.889 [       t1] [검증 시작] 출금액: 800, 잔액: 1000
            17:15:38.890 [       t1] [검증 완료] 출금액: 800, 잔액: 1000
            17:15:39.369 [     main] t1 state: TIMED_WAITING
            17:15:39.369 [     main] t2 state: BLOCKED
            17:15:39.891 [       t1] [출금 완료] 출금액: 800, 변경 잔액: 200
            17:15:39.892 [       t1] 거래 종료
            17:15:39.893 [       t2] 거래 시작: BankAccountV2
            17:15:39.895 [       t2] [검증 시작] 출금액: 800, 잔액: 200
            17:15:39.896 [       t2] [검증 실패] 출금액: 800, 잔액: 200
            17:15:39.897 [     main] 최종 잔액: 200
         */

    }

}
